package com.example.roomrecyclerview

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
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

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = UserAdapter()

        binding.btnSearch.setOnClickListener {
            binding.fackeScreen.visibility= View.INVISIBLE
            read()
        }
    }


    private fun read() {
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