package com.student.tutorial.core.data.product

import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.GET_All_PRODUCT
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetAllProductsTutor {

    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getAllProductsTutorFlow() = flow {
        emit(ResultContainer.ResultLoadContainer(AllProductsTutorData(resultHttp = ResultHttp.LOADING)))
        val resultObject =
            getRetrofitImplUseCase.getRetrofitAllProductsTutor(BASE_URL + URL_PATH_MAIN, GET_All_PRODUCT)

        when (resultObject) {
            is Result.Ok-> {
                emit(ResultContainer.ResultOkContainer(resultObject.value))
                debugLog(resultObject.value, "DEBUG_LOG_0099")
            }

            is Result.Error -> {
                debugLog(resultObject.exception)
                emit(ResultContainer.ResultErrorContainer(AllProductsTutorData(resultHttp = ResultHttp.ERROR)))
            }

            is Result.Exception -> {
                emit(ResultContainer.ResultExceptionContainer(AllProductsTutorData(resultHttp = ResultHttp.EXCEPTION)))
                debugLog(resultObject.exception.message)
            }

            else -> {
                emit(ResultContainer.ResultNotContainer(AllProductsTutorData(resultHttp = ResultHttp.EXCEPTION)))
            }
        }
    }
}