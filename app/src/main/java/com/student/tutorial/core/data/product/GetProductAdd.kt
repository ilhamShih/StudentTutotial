package com.student.tutorial.core.data.product

import com.student.tutorial.config.Constant.ADD_PRODUCT
import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.ProductData
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetProductAdd {

    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getProductAddFlow(
        hash: String,
        nameKey: String,
        comment: String,
        docName: String,
        responsible: String
    ) = flow {
        emit(ResultContainer.ResultLoadContainer(ProductData(resultHttp = ResultHttp.LOADING)))
        val resultJson = getRetrofitImplUseCase.getRetrofitAddProduct(
            BASE_URL + URL_PATH_MAIN, ADD_PRODUCT, hash, nameKey, comment, docName, responsible
        )

        when (resultJson) {
            is Result.Ok -> {
                emit(ResultContainer.ResultOkContainer(resultJson.value))
            }

            is Result.Error -> {
                debugLog(resultJson.exception)
                emit(ResultContainer.ResultErrorContainer(ProductData(resultHttp = ResultHttp.ERROR)))
            }

            is Result.Exception -> {
                emit(ResultContainer.ResultExceptionContainer(ProductData(resultHttp = ResultHttp.EXCEPTION)))
                debugLog(resultJson.exception.message)
            }

            else -> {
                emit(ResultContainer.ResultNotContainer(ProductData(resultHttp = ResultHttp.EXCEPTION)))
            }
        }
    }
}