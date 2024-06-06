package com.student.tutorial.presentation.action.adapter.product

import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.presentation.action.common.TypeClick

interface OnClickAdapterProduct {
    fun onClickProduct(them: AllProductsData.Product, typeClick: TypeClick) {
    }
}