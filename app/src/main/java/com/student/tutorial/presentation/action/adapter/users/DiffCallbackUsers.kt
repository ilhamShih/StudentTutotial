package com.student.tutorial.presentation.action.adapter.users

import androidx.recyclerview.widget.DiffUtil
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllUsersData

class DiffCallbackUsers : DiffUtil.ItemCallback<AllUsersData.Users>() {
    override fun areItemsTheSame(
        oldItem: AllUsersData.Users,
        newItem: AllUsersData.Users
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: AllUsersData.Users,
        newItem: AllUsersData.Users
    ): Boolean =
        oldItem == newItem
}