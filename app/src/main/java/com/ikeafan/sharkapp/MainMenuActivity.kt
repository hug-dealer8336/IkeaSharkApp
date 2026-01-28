package com.ikeafan.sharkapp

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val list = findViewById<LinearLayout>(R.id.menuList)

        // Build menu items from the same imageItems defined in MainActivity
        val items = listOf("IKEA Shark", "Dodo")
        items.forEachIndexed { index, name ->
            val tv = TextView(this).apply {
                text = name
                textSize = 18f
                setPadding(32, 24, 32, 24)
                setBackgroundResource(android.R.attr.selectableItemBackground)
            }
            tv.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("image_index", index)
                startActivity(intent)
            }
            list.addView(tv)
        }
    }
}

