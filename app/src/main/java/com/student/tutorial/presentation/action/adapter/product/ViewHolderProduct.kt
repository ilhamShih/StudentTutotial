package com.student.tutorial.presentation.action.adapter.product

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.student.tutorial.R

class ViewHolderProduct(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtNumber: TextView = itemView.findViewById(R.id.txt_number)
    val addBy: TextView = itemView.findViewById(R.id.add_by)
    val editBtn: ImageView = itemView.findViewById(R.id.edit_btn)
    val deleteBtn: ImageView = itemView.findViewById(R.id.delete_btn)
    val infoBtn: ImageView = itemView.findViewById(R.id.info_btn)
}