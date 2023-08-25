package com.example.roomrecyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.roomrecyclerview.databinding.ActivitySainBinding
import com.example.roomrecyclerview.databinding.ActivitySubBinding

class SainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sain)

        val sendclick = findViewById<Button>(R.id.sendbutton)
        val sendword = findViewById<EditText>(R.id.et)

        sendclick.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            val pass = sendword.text.toString()
            if (pass == ""){
                Toast.makeText(this@SainActivity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                intent.putExtra("word",pass)
                startActivity(intent)}
            }
        }
    }
