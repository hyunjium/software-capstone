package com.example.roomrecyclerview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
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

        binding.nebu.setOnClickListener {
            val text = binding.editText.text.toString()
            val intent = Intent(this@MainActivity, SainActivity::class.java)
            intent.putExtra("Data", text)
            startActivity(intent)
        }

        val colors = arrayOf("메뉴", "가게")
        val colorSpinner: Spinner = binding.colorSpinner
        val adapter: ArrayAdapter<Any?> = ArrayAdapter(
            this,
            R.layout.spinner_item, colors
        )
        adapter.setDropDownViewResource(R.layout.spinner_item)
        colorSpinner.adapter = adapter

        val selects = arrayOf("별점순", "리뷰순")
        val selectSpinner: Spinner = binding.selectSpinner
        val s_adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            R.layout.spinner_item, selects
        )
        s_adapter.setDropDownViewResource(R.layout.spinner_item)
        selectSpinner.adapter = s_adapter

        binding.btnSearch.setOnClickListener {
            binding.fackeScreen.visibility = View.INVISIBLE
            val text: String = colorSpinner.selectedItem.toString()
            val select_text: String = selectSpinner.selectedItem.toString()
            if (text == "메뉴" && select_text == "별점순") {
                menu_star()
            } else if (text == "가게" && select_text == "별점순") {
                store_star()
            } else if (text == "메뉴" && select_text == "리뷰순") {
                menu_review()
            } else {
                store_review()
            }
        }
    }


    private fun menu_star() {
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

    private fun store_star() {
        job = lifecycleScope.launch() {
            withContext(Dispatchers.IO) {
                val searchword = binding.editText.text.toString()
                val userList = AppDatabase(this@MainActivity).studentDao().findByRoll2(searchword)
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

    private fun menu_review() {
        job = lifecycleScope.launch() {
            withContext(Dispatchers.IO) {
                val searchword = binding.editText.text.toString()
                val userList = AppDatabase(this@MainActivity).studentDao().findByRoll3(searchword)
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

    private fun store_review() {
        job = lifecycleScope.launch() {
            withContext(Dispatchers.IO) {
                val searchword = binding.editText.text.toString()
                val userList = AppDatabase(this@MainActivity).studentDao().findByRoll4(searchword)
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