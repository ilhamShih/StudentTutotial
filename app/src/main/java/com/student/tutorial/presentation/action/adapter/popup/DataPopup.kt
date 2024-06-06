package com.student.tutorial.presentation.action.adapter.popup

import com.student.tutorial.presentation.action.common.TypeClass

data class DataPopup(
    val typeClass: TypeClass,
    val allNameNormal: String = "",
    val name: String = "",
    val key: String
)
