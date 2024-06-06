package com.student.tutorial.presentation.action.adapter.product

import androidx.recyclerview.widget.DiffUtil
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData

class DiffCallbackProduct : DiffUtil.ItemCallback<AllProductsData.Product>() {
    override fun areItemsTheSame(
        oldItem: AllProductsData.Product,
        newItem: AllProductsData.Product
    ): Boolean =
        oldItem.nameKey == newItem.nameKey

    override fun areContentsTheSame(
        oldItem: AllProductsData.Product,
        newItem: AllProductsData.Product
    ): Boolean =
        oldItem == newItem
}