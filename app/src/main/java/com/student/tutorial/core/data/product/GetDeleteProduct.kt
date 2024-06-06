package com.student.tutorial.core.data.product

import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.DELETE_PRODUCT
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.DeleteProduct
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetDeleteProduct {

    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getDeleteProductsFlow(hash: String, nameKey: String) = flow {
        emit(ResultContainer.ResultLoadContainer(DeleteProduct(resultHttp = ResultHttp.LOADING)))
        val resultJson =
            getRetrofitImplUseCase.getRetrofitDeleteProduct(
                BASE_URL + URL_PATH_MAIN,
                DELETE_PRODUCT,
                hash,
                nameKey
            )

        when (resultJson) {
            is Result.Ok -> {
                emit(ResultContainer.ResultOkContainer(resultJson.value))
                debugLog(resultJson.value, "DEBUG_LOG_0099")
            }

            is Result.Error -> {
                debugLog(resultJson.exception)
                emit(ResultContainer.ResultErrorContainer(DeleteProduct(resultHttp = ResultHttp.ERROR)))
            }

            is Result.Exception -> {
                emit(ResultContainer.ResultExceptionContainer(DeleteProduct(resultHttp = ResultHttp.EXCEPTION)))
                debugLog(resultJson.exception.message)
            }

            else -> {
                emit(ResultContainer.ResultNotContainer(DeleteProduct(resultHttp = ResultHttp.EXCEPTION)))
            }
        }
    }
}