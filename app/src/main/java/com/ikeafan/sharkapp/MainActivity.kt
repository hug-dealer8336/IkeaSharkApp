package com.ikeafan.sharkapp

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Spinner
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity() {

    data class ImageItem(
        val name: String,
        val imageResId: Int,
        val firebasePath: String
    )

    private val imageItems = listOf(
        ImageItem("IKEA Shark", R.drawable.ikea_shark, "shark_likes"),
        ImageItem("Dodo", R.drawable.dodo_no_backgroung, "dodo_likes")
    )

    private lateinit var likeButton: Button
    private lateinit var likeCountText: TextView
    private lateinit var imageView: ImageView
    private lateinit var imageSpinner: Spinner
    private lateinit var titleText: TextView
    private lateinit var subtitleText: TextView

    private val database = FirebaseDatabase.getInstance()
    private lateinit var currentLikesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        likeButton = findViewById(R.id.likeButton)
        likeCountText = findViewById(R.id.likeCountText)
        imageView = findViewById(R.id.imageView)
        imageSpinner = findViewById(R.id.imageSpinner)
        titleText = findViewById(R.id.titleText)
        subtitleText = findViewById(R.id.subtitleText)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, imageItems.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        imageSpinner.adapter = adapter

        // Determine start index early and initialize currentLikesRef to avoid uninitialized access
        val startIndex = intent.getIntExtra("image_index", 0)
        currentLikesRef = database.getReference(imageItems.getOrNull(startIndex)?.firebasePath
            ?: imageItems[0].firebasePath)
        // Attach listener for the initial selection
        attachLikeCounterListener()

        imageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = imageItems[position]
                imageView.setImageResource(selectedItem.imageResId)
                currentLikesRef = database.getReference(selectedItem.firebasePath)
                attachLikeCounterListener()
                // Update UI text to match selected image
                titleText.text = "${selectedItem.name} FAN"
                subtitleText.text = "people love the ${selectedItem.name}!"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set initial selection from intent (menu) or default to first item
        imageSpinner.setSelection(startIndex)

        // Handle like button click
        likeButton.setOnClickListener {
            // Animate the button
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.like_pulse)
            likeButton.startAnimation(scaleAnimation)
            
            // Animate the image
            val imageAnimation = AnimationUtils.loadAnimation(this, R.anim.shark_bounce)
            imageView.startAnimation(imageAnimation)

            // Increment the counter (only if initialized)
            if (::currentLikesRef.isInitialized) {
                currentLikesRef.get().addOnSuccessListener { snapshot ->
                    val currentCount = snapshot.getValue(Long::class.java) ?: 0
                    currentLikesRef.setValue(currentCount + 1)
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to like. Check your internet!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please select an item first.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun attachLikeCounterListener() {
        currentLikesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val count = snapshot.getValue(Long::class.java) ?: 0
                likeCountText.text = formatNumber(count)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Failed to load likes", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun formatNumber(number: Long): String {
        return when {
            number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000.0)
            number >= 1_000 -> String.format("%.1fK", number / 1_000.0)
            else -> number.toString()
        }
    }
}
