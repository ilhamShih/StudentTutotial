package com.student.tutorial.core.model

import com.google.gson.annotations.SerializedName

data class AllUsersData(
    @SerializedName("result")
    val result: Boolean? = false,
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("users")
    val users: List<Users>? = null,
    @SerializedName("resultHttp")
    val resultHttp: ResultHttp? = ResultHttp.LOADING

) {
    data class Users(
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("login")
        val login: String? = "",
        @SerializedName("password")
        val password: String? = "",
        @SerializedName("user_type")
        val userType: Int? = 0,
        @SerializedName("name")
        val name: String? = ""
    )
}