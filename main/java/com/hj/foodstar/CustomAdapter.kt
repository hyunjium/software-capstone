package com.hj.foodstar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hj.foodstar.databinding.EachStoreBinding

class CustomAdapter(private val data: ArrayList<String>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    // Adapter의 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(EachStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        //생성된 ViewHolder의 bind() 메서드 실행 (이 부분은 RecyclerView의 각 포지션 마다 실행됨)
        holder.bind()
    }

    // RecyclerView 아이템 크기
    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: EachStoreBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            // 해당되는 포지션의 Item으로 TextView의 Text를 수정
            binding.eachName.text = data[adapterPosition]
        }
    }
}