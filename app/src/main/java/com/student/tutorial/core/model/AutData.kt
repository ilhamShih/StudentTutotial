package com.student.tutorial.core.model

import com.google.gson.annotations.SerializedName

data class AutData(
    @SerializedName("result")
    val result: Boolean? = false,
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("user")
    val user: User? = null,
    @SerializedName("resultHttp")
    val resultHttp: ResultHttp? = ResultHttp.LOADING

) {
    data class User(
        @SerializedName("user_type")
        val userType: Int? = 0,
        @SerializedName("hashCode")
        val hashCode: String? = "",
        @SerializedName("name")
        val name: String? = ""
    )

}
