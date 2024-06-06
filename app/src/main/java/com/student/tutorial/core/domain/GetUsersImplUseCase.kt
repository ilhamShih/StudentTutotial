package com.student.tutorial.core.domain

import com.student.tutorial.core.impl.GetListenerApi

class GetUsersImplUseCase(private val getListenerApi: GetListenerApi) {

    /** --------- Получить всех пользователей --------- */
    suspend fun getAllUsers(hash: String) =
        getListenerApi.getAllUsers(hash)

    /** --------- Редактировать пользователя --------- */
    suspend fun getUserEdit(
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String,
    ) = getListenerApi.getUserEdit(hash, idUser, typeUser, nameUser, loginUser, passUser)

    /** --------- Удалить пользователя --------- */
    suspend fun getDeleteUser(hash: String, idUser: Int) =
        getListenerApi.getUserDelete(hash, idUser)

    /** --------- Добавить пользователя --------- */
    suspend fun getAddNewUser(
        hash: String,
        loginUser: String,
        passUser: String,
        typeUser: Int,
        nameUser: String
    ) = getListenerApi.getAddNewUser(
        hash,
        loginUser,
        passUser,
        typeUser,
        nameUser
    )
}