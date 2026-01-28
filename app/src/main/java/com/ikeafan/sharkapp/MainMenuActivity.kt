package com.ikeafan.sharkapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_main_menu)

            val recycler = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.menuRecycler)
            val items = listOf(
                Pair(R.drawable.ikea_shark, "IKEA Shark"),
                Pair(R.drawable.dodo_no_backgroung, "Dodo")
            )
            val adapter = MenuAdapter(items) { index ->
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("image_index", index)
                startActivity(intent)
            }
            recycler.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
            recycler.adapter = adapter
        } catch (e: Exception) {
            Log.e("MainMenuActivity", "Startup error", e)
            AlertDialog.Builder(this)
                .setTitle("App error")
                .setMessage(Log.getStackTraceString(e))
                .setPositiveButton("OK", null)
                .show()
        }
    }
}

