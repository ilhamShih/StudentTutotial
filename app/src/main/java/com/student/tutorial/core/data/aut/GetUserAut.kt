package com.student.tutorial.core.data.aut

import com.student.tutorial.config.Constant.AUTHORIZATION
import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.AutData
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetUserAut {
    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getUserAutFlow(mLogin: String, mPass: String) = flow {
        //  https://fullfe.ru/api/main.php?command=authorization&login=adminbase6739&pass=74646ugfhfjke6r6r7
        emit(ResultContainer.ResultLoadContainer(AutData(resultHttp = ResultHttp.LOADING)))
        val resultJson = getRetrofitImplUseCase.getRetrofitUserAut(
            BASE_URL + URL_PATH_MAIN, AUTHORIZATION, mLogin, mPass
        )

        when (resultJson) {
            is Result.Ok -> {
               // debugLog(resultJson.value)
                emit(ResultContainer.ResultOkContainer(resultJson.value))
            }

            is Result.Error -> {
                debugLog(resultJson.exception)
                emit(ResultContainer.ResultErrorContainer(AutData(resultHttp = ResultHttp.ERROR)))
            }

            is Result.Exception -> {
                emit(ResultContainer.ResultExceptionContainer(AutData(resultHttp = ResultHttp.EXCEPTION)))
                debugLog(resultJson.exception.message)
            }

            else -> {
                emit(ResultContainer.ResultNotContainer(AutData(resultHttp = ResultHttp.EXCEPTION)))
            }
        }
    }
}