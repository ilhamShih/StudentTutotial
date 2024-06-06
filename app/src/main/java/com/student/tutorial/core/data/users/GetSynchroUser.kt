package com.student.tutorial.core.data.users

import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.EDIT_USER
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.config.Constant.USER_AUT
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.model.SynchroAutData
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetSynchroUser {
    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getSynchroUserFlow(
        hash: String
    ) = flow {
        emit(ResultContainer.ResultLoadContainer(SynchroAutData(resultHttp = ResultHttp.LOADING)))
        val resultJson = getRetrofitImplUseCase.getSynchroUser(
            BASE_URL + URL_PATH_MAIN,
            USER_AUT, hash
        )

        when (resultJson) {
            is Result.Ok -> {
                emit(ResultContainer.ResultOkContainer(resultJson.value))
            }

            is Result.Error -> {
                debugLog(resultJson.exception)
                emit(ResultContainer.ResultErrorContainer(SynchroAutData(resultHttp = ResultHttp.ERROR)))
            }

            is Result.Exception -> {
                emit(ResultContainer.ResultExceptionContainer(SynchroAutData(resultHttp = ResultHttp.EXCEPTION)))
                debugLog(resultJson.exception.message)
            }

            else -> {
                emit(ResultContainer.ResultNotContainer(SynchroAutData(resultHttp = ResultHttp.EXCEPTION)))
            }
        }
    }
}