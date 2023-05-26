package com.example.roomrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var list = mutableListOf<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item_user_view_holder, parent, false)

        return UserViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.tvFirstName.text = user.firstName
        holder.tvLastName.text = user.lastName
        holder.tvStarNum.text = user.rollNo.toString()
    }

    fun setData(data: List<Student>){
        list.apply {
         clear()
         addAll(data)
        }
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvFirstName: TextView = itemView.findViewById(R.id.tv_first_name)
        val tvLastName: TextView = itemView.findViewById(R.id.tv_last_name)
        val tvStarNum: TextView = itemView.findViewById(R.id.tv_star_num)
    }
}