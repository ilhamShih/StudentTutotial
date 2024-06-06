package com.student.tutorial.core.data.users

import com.student.tutorial.config.Constant.BASE_URL
import com.student.tutorial.config.Constant.EDIT_USER
import com.student.tutorial.config.Constant.URL_PATH_MAIN
import com.student.tutorial.core.data.common.GetRetrofitIml
import com.student.tutorial.core.domain.GetRetrofitImplUseCase
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.model.UserAddEditDelete
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.debugLog
import kotlinx.coroutines.flow.flow

class GetUserEdit {
    private val getRetrofitImplUseCase = GetRetrofitImplUseCase(GetRetrofitIml)

    suspend fun getEditUserFlow(
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String
    ) = flow {
        emit(ResultContainer.ResultLoadContainer(UserAddEditDelete(resultHttp = ResultHttp.LOADING)))
        val resultJson = getRetrofitImplUseCase.getRetrofitEditUser(
            BASE_URL + URL_PATH_MAIN,
            EDIT_USER,
            hash,
            idUser,
            typeUser,
            nameUser,
            loginUser,
            passUser
        )
        debugLog("$hash, $idUser, $typeUser, $nameUser, $loginUser, $passUser")
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