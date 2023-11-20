package com.example.roomrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class StartView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_view)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SainActivity::class.java))
            finish()
        },3000)

    }
}