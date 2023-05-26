package com.example.roomrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomrecyclerview.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            read()
        }
    }


    private fun read(){
        job = lifecycleScope.launch() {
            withContext(Dispatchers.IO) {
                val searchword = binding.editText.text.toString()
                val userList = AppDatabase(this@MainActivity).studentDao().findByRoll(searchword)
                runOnUiThread {
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = UserAdapter().apply {
                            setData(userList)
                        }
                    }
                }
            }
        }
    }
}