package com.example.roomrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var list = mutableListOf<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item_user_view_holder, parent, false)

        return UserViewHolder(view, list)
    }

    override fun getItemCount() = list.size

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.tvFirstName.text = user.firstName
        holder.tvLastName.text = user.lastName
        holder.tvStarNum.text = user.rollNo.toString()
        holder.tvReviewNum.text = user.reviewNum.toString() + "개"
        //holder.tvimage.setImageResource(R.dr)
//        holder.apply {
//            Glide.with(itemView)
//                .load(user.imageUrl)
//                .override(200, 200)
//                .into(tvimage)
//        }
    }

    fun setData(data: List<Student>){
        list.apply {
         clear()
         addAll(data)
        }
    }

    class UserViewHolder(itemView: View, list: MutableList<Student>): RecyclerView.ViewHolder(itemView){
        val tvFirstName: TextView = itemView.findViewById(R.id.tv_first_name)
        val tvLastName: TextView = itemView.findViewById(R.id.tv_last_name)
        val tvStarNum: TextView = itemView.findViewById(R.id.tv_star_num)
        val tvReviewNum: TextView = itemView.findViewById(R.id.tv_review_num)
        val tvimage: ImageView = itemView.findViewById(R.id.tv_image)


        init {
            itemView.setOnClickListener{ v:View ->
                val url = list[position].storeUrl
                v.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
    }
}