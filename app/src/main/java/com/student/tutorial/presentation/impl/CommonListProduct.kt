package com.student.tutorial.presentation.impl

import com.student.tutorial.core.model.AllProductsData

object CommonListProduct {
    private var _listLogger: MutableList<AllProductsData.Product?>? = null
    val listLogger get() = _listLogger ?: throw RuntimeException("listLogger")

    fun initListLogger(list:MutableList<AllProductsData.Product?>) {
        _listLogger = mutableListOf()
        listLogger.addAll(list)
    }

    fun unInitListLogger() {
        listLogger.clear()
        _listLogger = null
    }

}