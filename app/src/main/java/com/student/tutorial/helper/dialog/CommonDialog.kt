package com.student.tutorial.helper.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.student.tutorial.R
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData

object CommonDialog {

    fun Context.dialogFilter(view: View, function: (String) -> Unit) {
        val parent = view.parent as ViewGroup?
        parent?.removeView(view)
        AlertDialog.Builder(this).apply {
            setCancelable(false)
            setView(view)
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            val filterByCode = view.findViewById(R.id.filter_by_code) as MaterialButton
            val filterReset = view.findViewById(R.id.filter_reset) as MaterialButton
            val btnCloseDialog = view.findViewById(R.id.close) as ImageView
            filterByCode.setOnClickListener {
                dismiss()
                function.invoke(filterByCode.text.toString())
            }
            filterReset.setOnClickListener {
                dismiss()
                function.invoke("")
            }
            btnCloseDialog.setOnClickListener {
                dismiss()
            }

        }.show()
    }

    fun Context.dialogInfoProduct(
        view: View,
        product: AllProductsData.Product,
        function: (DialogCommand) -> Unit
    ) {
        val parent = view.parent as ViewGroup?
        parent?.removeView(view)
        AlertDialog.Builder(this).apply {
            setCancelable(false)
            setView(view)
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            val btnCloseDialog = view.findViewById(R.id.close) as ImageView
            val txtDocKey = view.findViewById(R.id.txt_doc_key) as TextView
            val txtDocName = view.findViewById(R.id.txt_doc_name) as TextView
            val txtComment = view.findViewById(R.id.txt_comment) as TextView

            txtDocKey.text = "Документ: ${product.nameKey}"
            txtDocName.text = "${product.docName}"
            txtComment.text = "${product.comment}"
            btnCloseDialog.setOnClickListener {
                dismiss()
                function.invoke(DialogCommand.NOT)
            }
        }.show()
    }


    fun Context.dialogDelete(
        view: View,
        function: (DialogCommand) -> Unit
    ) {
        val parent = view.parent as ViewGroup?
        parent?.removeView(view)
        AlertDialog.Builder(this).apply {
            setCancelable(false)
            setView(view)
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            val btnCloseDialog = view.findViewById(R.id.close) as ImageView
            val btnOk = view.findViewById(R.id.btn_ok) as MaterialButton

            btnOk.setOnClickListener {
                dismiss()
                function.invoke(DialogCommand.OK)
            }

            btnCloseDialog.setOnClickListener {
                dismiss()
                function.invoke(DialogCommand.NOT)
            }
        }.show()
    }

}