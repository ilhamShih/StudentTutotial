package com.student.tutorial.core.domain

import com.student.tutorial.core.impl.GetListenerApi

class GetAuthorizationImpUseCase(private val getListenerApi: GetListenerApi) {

    /** --------- Авторизуемся  --------- */
    suspend fun getAutUser(mLogin: String, mPass: String) =
        getListenerApi.getUserAut(mLogin, mPass)

    /** --------- Синхронизация пользователя --------- */
    suspend fun getSynchroUser(hash: String) = getListenerApi.getSynchroUser(hash)

}