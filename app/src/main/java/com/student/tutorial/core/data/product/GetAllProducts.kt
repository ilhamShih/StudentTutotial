package com.student.tutorial.core.data.product

import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.GET_All_PRODUCT
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetAllProducts {

    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getAllProductsFlow() = flow {
        emit(ResultContainer.ResultLoadContainer(AllProductsData(resultHttp = ResultHttp.LOADING)))
        val resultJson =
            getRetrofitImplUseCase.getRetrofitAllProducts(BASE_URL + URL_PATH_MAIN, GET_All_PRODUCT)

        when (resultJson) {
            is Result.Ok -> {
                emit(ResultContainer.ResultOkContainer(resultJson.value))
                debugLog(resultJson.value, "DEBUG_LOG_0099")
            }

            is Result.Error -> {
                debugLog(resultJson.exception)
                emit(ResultContainer.ResultErrorContainer(AllProductsData(resultHttp = ResultHttp.ERROR)))
            }

            is Result.Exception -> {
                emit(ResultContainer.ResultExceptionContainer(AllProductsData(resultHttp = ResultHttp.EXCEPTION)))
                debugLog(resultJson.exception.message)
            }

            else -> {
                emit(ResultContainer.ResultNotContainer(AllProductsData(resultHttp = ResultHttp.EXCEPTION)))
            }
        }
    }
}