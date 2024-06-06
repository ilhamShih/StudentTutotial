package com.student.tutorial.core.model

import com.google.gson.annotations.SerializedName

data class SynchroAutData(
    @SerializedName("result")
    val result: Boolean? = false,
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("available")
    val available: Boolean? = false,
    @SerializedName("user_type")
    val userType: Int? = 0,
    @SerializedName("resultHttp")
    val resultHttp: ResultHttp? = ResultHttp.LOADING
)


