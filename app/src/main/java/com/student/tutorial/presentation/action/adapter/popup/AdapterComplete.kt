package com.student.tutorial.presentation.action.adapter.popup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filterable
import android.widget.TextView
import com.student.tutorial.R

class AdapterComplete(
    private val mContext: Context,
    private val resource: Int,
    private val onClickUserType: OnClickAdapterPopup
) :
    ArrayAdapter<DataPopup>(mContext, resource), Filterable {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(mContext)
            .inflate(resource, parent, false)
        val item = getItem(position)
        if (item != null) {
            val btnSpinner: TextView = view.findViewById(R.id.field_number)
            btnSpinner.text = item.allNameNormal
            btnSpinner.setOnClickListener {
                onClickUserType.onClickPopup(item)
            }
        }
        return view
    }
}
