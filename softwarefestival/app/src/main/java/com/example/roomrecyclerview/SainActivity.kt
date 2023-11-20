package com.example.roomrecyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomrecyclerview.databinding.ActivitySainBinding

class SainActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sendclick = findViewById<Button>(R.id.sendbutton)
        val sendword = findViewById<EditText>(R.id.et)
        val colors = arrayOf("메뉴", "가게")
        val colorSpinner: Spinner = binding.sainSpinner
        val adapter: ArrayAdapter<Any?> = ArrayAdapter(
            this,
            R.layout.spinner_item, colors
        )
        adapter.setDropDownViewResource(R.layout.spinner_item)
        colorSpinner.adapter = adapter

        sendclick.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            val sainSpinnerValue = colorSpinner.selectedItem.toString()
            val pass = sendword.text.toString()
            if (pass == "") {
                Toast.makeText(this@SainActivity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                intent.putExtra("word", pass)
                intent.putExtra("spinner", sainSpinnerValue)
                startActivity(intent)
            }
        }
    }
}
