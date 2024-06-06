package com.student.tutorial.core.model

import com.google.gson.annotations.SerializedName

data class DeleteProduct(
    @SerializedName("result")
    val result: Boolean? = false,
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("resultHttp")
    val resultHttp: ResultHttp? = ResultHttp.LOADING
)