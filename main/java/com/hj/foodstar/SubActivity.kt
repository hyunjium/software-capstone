package com.hj.foodstar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hj.foodstar.databinding.SearchViewBinding


class SubActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { SearchViewBinding.inflate(layoutInflater) }
    private val arrayData = ArrayList<String>()
    private val arrayStore = ArrayList<String>()
    private var recyclerAdapter: CustomAdapter? = null

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btn.id -> {
                if (binding.searchName.text.isNullOrEmpty()) {
                    Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
                else {
                    arrayData.clear()
                    val checkItem = binding.searchName.text.toString()
                    for (a in arrayStore) {
                        if (checkItem in a) {
                            arrayData.add(a)
                        }
                        recyclerAdapter?.notifyDataSetChanged()
                        recyclerAdapter?.notifyItemInserted(arrayData.size - 1)
                    }
                    //arrayData.add(binding.searchName.text.toString())
                    //recyclerAdapter?.notifyItemInserted(arrayData.size - 1)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        arrayStore.add("hi")
        arrayStore.add("hello")
        arrayStore.add("same")

        recyclerAdapter = CustomAdapter(arrayData)
        binding.recyclerView.adapter = recyclerAdapter

        binding.btn.setOnClickListener(this)
    }
}