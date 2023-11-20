package com.example.roomrecyclerview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomrecyclerview.databinding.ActivitySubBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubBinding
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = UserAdapter()

        val pass = findViewById<EditText>(R.id.edit_text2)
        val pw = intent.getStringExtra("word")
        pass.setText(pw)
        val get_spinner = intent.getStringExtra("spinner")
        var colors = arrayOf("메뉴", "가게")
        if (get_spinner == "메뉴") {
            colors = arrayOf("메뉴", "가게")
        } else {
            colors = arrayOf("가게", "메뉴")
        }
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

        if (get_spinner == "메뉴") {
            menu_star()
        } else {
            store_star()
        }


        binding.btnSearch.setOnClickListener {
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
                val searchword = binding.editText2.text.toString()
                val userList = AppDatabase(this@SubActivity).studentDao().findByRoll(searchword)
                runOnUiThread {
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@SubActivity)
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
                val searchword = binding.editText2.text.toString()
                val userList = AppDatabase(this@SubActivity).studentDao().findByRoll2(searchword)
                runOnUiThread {
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@SubActivity)
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
                val searchword = binding.editText2.text.toString()
                val userList = AppDatabase(this@SubActivity).studentDao().findByRoll3(searchword)
                runOnUiThread {
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@SubActivity)
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
                val searchword = binding.editText2.text.toString()
                val userList = AppDatabase(this@SubActivity).studentDao().findByRoll4(searchword)
                runOnUiThread {
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@SubActivity)
                        adapter = UserAdapter().apply {
                            setData(userList)
                        }
                    }
                }
            }
        }
    }


}