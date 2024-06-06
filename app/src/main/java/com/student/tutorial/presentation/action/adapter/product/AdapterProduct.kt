package com.student.tutorial.presentation.action.adapter.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import com.student.tutorial.R
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.helper.save.Save.isUserType
import com.student.tutorial.presentation.action.common.TypeClick


class AdapterProduct(
    private val onClickAdapterProduct: OnClickAdapterProduct,
    val context: Context
) :
    ListAdapter<AllProductsData.Product, ViewHolderProduct>(DiffCallbackProduct()),
    Filterable {
    private var _listFilter: List<AllProductsData.Product>? = null
    val listFilter get() = _listFilter ?: throw RuntimeException("_listFilter")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduct {
        val v: View = LayoutInflater.from(parent.context).inflate(
            when (viewType) {
                1 -> R.layout.item_product
                0 -> R.layout.item_product_user
                else -> R.layout.item_product_user

            }, parent, false
        )
        return ViewHolderProduct(v)
    }

    override fun onBindViewHolder(holder: ViewHolderProduct, position: Int) {
        val item = getItem(position)
        holder.txtNumber.text = item.nameKey
        holder.addBy.text = "Добавил: ${item.responsible}"

        holder.editBtn.setOnClickListener {
            onClickAdapterProduct.onClickProduct(item, TypeClick.EDIT)
        }
        holder.infoBtn.setOnClickListener {
            onClickAdapterProduct.onClickProduct(item, TypeClick.INFO)
        }
        holder.deleteBtn.setOnClickListener {
            onClickAdapterProduct.onClickProduct(item, TypeClick.DELETE)
        }

    }

    override fun getItem(position: Int): AllProductsData.Product {
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

    fun setFilterList(list: List<AllProductsData.Product>?) {
        _listFilter = list
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                return FilterResults().apply {
                    values = if (constraint.isNullOrEmpty())
                        listFilter
                    else
                        onFilter(listFilter, constraint.toString())
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                submitList(results?.values as? List<AllProductsData.Product>)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (context.isUserType) {
            1 -> 1
            else -> 0
        }
    }

    fun onFilter(
        list: List<AllProductsData.Product>,
        constraint: String
    ): List<AllProductsData.Product> {
        val filteredList = list.filter {
            it.nameKey?.lowercase()?.contains(constraint.lowercase()) == true
        }

        return filteredList

    }

}