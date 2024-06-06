package com.student.tutorial.presentation.action.adapter.users

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.student.tutorial.R

class ViewHolderUsers(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val loginUser: TextView = itemView.findViewById(R.id.login_user)
    val nameUser: TextView = itemView.findViewById(R.id.name_user)
    val editBtn: ImageView = itemView.findViewById(R.id.edit_btn)
    val deleteBtn: ImageView = itemView.findViewById(R.id.delete_btn)

}