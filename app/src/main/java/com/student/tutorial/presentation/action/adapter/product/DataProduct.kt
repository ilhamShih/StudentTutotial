package com.student.tutorial.presentation.action.adapter.product

import com.student.tutorial.presentation.action.common.TypeClick

data class DataProduct(
    val typeClick: TypeClick,
    val allNameNormal: String = "",
    val name: String = "",
    val key: String
)
