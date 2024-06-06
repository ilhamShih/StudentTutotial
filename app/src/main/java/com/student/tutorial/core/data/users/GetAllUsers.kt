package com.student.tutorial.core.data.users

import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.GET_All_USERS
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.AllUsersData
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetAllUsers {
    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getAllUsersFlow(hash: String) = flow {
        emit(ResultContainer.ResultLoadContainer(AllUsersData(resultHttp = ResultHttp.LOADING)))
        val resultJson = getRetrofitImplUseCase.getRetrofitAllUsers(
            BASE_URL + URL_PATH_MAIN, GET_All_USERS, hash
        )

        when (resultJson) {
            is Result.Ok -> {
                emit(ResultContainer.ResultOkContainer(resultJson.value))
            }

            is Result.Error -> {
                debugLog(resultJson.exception)
                emit(ResultContainer.ResultErrorContainer(AllUsersData(resultHttp = ResultHttp.ERROR)))
            }

            is Result.Exception -> {
                emit(ResultContainer.ResultExceptionContainer(AllUsersData(resultHttp = ResultHttp.EXCEPTION)))
                debugLog(resultJson.exception.message)
            }

            else -> {
                emit(ResultContainer.ResultNotContainer(AllUsersData(resultHttp = ResultHttp.EXCEPTION)))
            }
        }
    }
}