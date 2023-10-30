package com.example.chatflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var getStarted: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()


        getStarted = findViewById(R.id.h)

        getStarted.setOnClickListener()
        {
            val intent = Intent(this, LoginPage:: class.java)
            finish()
            startActivity(intent)
        }

    }
}