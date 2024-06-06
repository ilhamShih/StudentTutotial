package com.student.tutorial.core.model

import com.google.gson.annotations.SerializedName

data class AllProductsTutorData(
    @SerializedName("result")
    val result: Boolean? = false,
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("product")
    val product: List<Product>? = null,
    @SerializedName("resultHttp")
    val resultHttp: ResultHttp? = ResultHttp.LOADING

) {
    data class Product(
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("name_key")
        val nameKey: String? = "",
        @SerializedName("comment")
        val comment: String? = "",
        @SerializedName("doc_name")
        val docName: String? = "",
        @SerializedName("responsible")
        val responsible: String? = ""
    )
}