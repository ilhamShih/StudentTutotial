package com.student.tutorial.presentation.action.adapter.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.student.tutorial.R
import com.student.tutorial.core.model.AllUsersData
import com.student.tutorial.presentation.action.common.TypeClick

class AdapterUsers(private val onClickAdapterUsers: OnClickAdapterUsers) :
    ListAdapter<AllUsersData.Users, ViewHolderUsers>(DiffCallbackUsers()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUsers {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return ViewHolderUsers(v)
    }

    override fun onBindViewHolder(holder: ViewHolderUsers, position: Int) {
        val item = getItem(position)
        holder.nameUser.text = "Статус: ${item.userType?.getStatus()}"
        holder.loginUser.text = item.name

        holder.editBtn.setOnClickListener {
            onClickAdapterUsers.onClickUsers(item, TypeClick.EDIT)
        }
        holder.deleteBtn.setOnClickListener {
            onClickAdapterUsers.onClickUsers(item, TypeClick.DELETE)
        }

    }

    private fun Int.getStatus() = if (this == 1) "Администратор" else "Гость"

    override fun getItem(position: Int): AllUsersData.Users {
        return if (position >= currentList.size)
            currentList.last()
        else
            currentList[position]
    }

    override fun getItemCount(): Int {
        return if (currentList.isNotEmpty())
            currentList.size
        else
            0
    }


}