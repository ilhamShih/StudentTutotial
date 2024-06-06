package com.student.tutorial.core.data.users

import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.DELETE_USER
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.model.UserAddEditDelete
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetUserDelete {
    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getDeleteUserFlow(
        hash: String,
        idUser: Int

    ) = flow {
        emit(ResultContainer.ResultLoadContainer(UserAddEditDelete(resultHttp = ResultHttp.LOADING)))
        val resultJson = getRetrofitImplUseCase.getRetrofitDeleteUser(
            BASE_URL + URL_PATH_MAIN,
            DELETE_USER,
            hash,
            idUser

        )

        when (resultJson) {
            is Result.Ok -> {
                emit(ResultContainer.ResultOkContainer(resultJson.value))
            }

            is Result.Error -> {
                debugLog(resultJson.exception)
                emit(ResultContainer.ResultErrorContainer(UserAddEditDelete(resultHttp = ResultHttp.ERROR)))
            }

            is Result.Exception -> {
                emit(ResultContainer.ResultExceptionContainer(UserAddEditDelete(resultHttp = ResultHttp.EXCEPTION)))
                debugLog(resultJson.exception.message)
            }

            else -> {
                emit(ResultContainer.ResultNotContainer(UserAddEditDelete(resultHttp = ResultHttp.EXCEPTION)))
            }
        }
    }
}