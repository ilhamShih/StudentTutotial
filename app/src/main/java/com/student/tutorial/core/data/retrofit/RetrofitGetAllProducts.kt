package com.student.tutorial.core.data.retrofit

import com.student.tutorial.core.impl.GetListenerRetrofitIml
import com.student.tutorial.core.net.common.HttpClient.apiRetrofitServiceGson
import com.student.tutorial.core.net.ext.CallAwait.awaitResult
import com.student.tutorial.helper.util.UtilTool.genUrl
import com.student.tutorial.helper.util.UtilTool.genUrlPath

class RetrofitGetAllProducts : GetListenerRetrofitIml{
    suspend fun allProducts(url: String, mCommand: String) =
        apiRetrofitServiceGson(genUrl(url)).getAllProductsTutor(
            host = genUrlPath(url),
            command = mCommand
        )?.awaitResult()
}