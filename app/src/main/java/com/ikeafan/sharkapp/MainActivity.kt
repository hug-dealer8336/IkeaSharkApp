package com.ikeafan.sharkapp

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var likeButton: Button
    private lateinit var likeCountText: TextView
    private lateinit var sharkImage: ImageView

    private val database = FirebaseDatabase.getInstance()
    private val likesRef = database.getReference("shark_likes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        likeButton = findViewById(R.id.likeButton)
        likeCountText = findViewById(R.id.likeCountText)
        sharkImage = findViewById(R.id.sharkImage)

        // Listen for like count changes in real-time
        likesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val count = snapshot.getValue(Long::class.java) ?: 0
                likeCountText.text = formatNumber(count)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Failed to load likes", Toast.LENGTH_SHORT).show()
            }
        })

        // Handle like button click
        likeButton.setOnClickListener {
            // Animate the button
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.like_pulse)
            likeButton.startAnimation(scaleAnimation)
            
            // Animate the shark
            val sharkAnimation = AnimationUtils.loadAnimation(this, R.anim.shark_bounce)
            sharkImage.startAnimation(sharkAnimation)

            // Increment the counter
            likesRef.get().addOnSuccessListener { snapshot ->
                val currentCount = snapshot.getValue(Long::class.java) ?: 0
                likesRef.setValue(currentCount + 1)
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to like. Check your internet!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun formatNumber(number: Long): String {
        return when {
            number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000.0)
            number >= 1_000 -> String.format("%.1fK", number / 1_000.0)
            else -> number.toString()
        }
    }
}
