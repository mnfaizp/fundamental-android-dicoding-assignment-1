package com.example.fundamentalsubmission1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalsubmission1.R
import com.example.fundamentalsubmission1.models.User

class UserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClick(data: User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userAva: ImageView = itemView.findViewById(R.id.img_avatar)
        val userFullName: TextView = itemView.findViewById(R.id.tv_name)
        val username: TextView = itemView.findViewById(R.id.tv_company)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar_url)
            .apply(RequestOptions().override(55, 55))
            .into(holder.userAva)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listUser[holder.adapterPosition])
        }

        holder.userFullName.text = user.login
        holder.username.text = user.url
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    fun addUsers(users: ArrayList<User>) {
        this.listUser.apply {
            clear()
            addAll(users)
        }
    }

}