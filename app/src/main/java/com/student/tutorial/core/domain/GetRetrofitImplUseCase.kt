package com.student.tutorial.core.domain

import com.student.tutorial.core.impl.GetListenerRetrofitIml

class GetRetrofitImplUseCase(private val getListenerRetrofitIml: GetListenerRetrofitIml) {

    /** --------- Авторизация --------- */
    suspend fun getRetrofitUserAut(
        url: String,
        mCommand: String,
        mLogin: String,
        mPass: String
    ) = getListenerRetrofitIml.getRetrofitUserAut(url, mCommand, mLogin, mPass)

    /** --------- Отправить данные в базу --------- */
    suspend fun getRetrofitAddProduct(
        url: String,
        mCommand: String,
        hash: String,
        nameKey: String,
        comment: String,
        docName: String,
        responsible: String
    ) = getListenerRetrofitIml.getRetrofitAddProduct(
        url,
        mCommand,
        hash,
        nameKey,
        comment,
        docName,
        responsible
    )



    /** --------- Удалить продукт из БД --------- */
    suspend fun getRetrofitDeleteProduct(
        url: String,
        mCommand: String,
        hash: String,
        nameKey: String,
    ) = getListenerRetrofitIml.getRetrofitDeleteProduct(url, mCommand, hash, nameKey)

    /** --------- Получить всех пользователей --------- */
    suspend fun getRetrofitAllUsers(
        url: String,
        mCommand: String,
        hash: String
    ) = getListenerRetrofitIml.getRetrofitAllUsers(url, mCommand, hash)

    /** --------- Редактировать пользователя --------- */
    suspend fun getRetrofitEditUser(
        url: String,
        mCommand: String,
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String,
    ) = getListenerRetrofitIml.getRetrofitUsersEdit(
        url,
        mCommand,
        hash,
        idUser,
        typeUser,
        nameUser,
        loginUser,
        passUser,
    )

    /** --------- Удалить пользователя --------- */
    suspend fun getRetrofitDeleteUser(
        url: String,
        mCommand: String,
        hash: String,
        idUser: Int
    ) = getListenerRetrofitIml.getRetrofitDeleteUser(url, mCommand, hash, idUser)

    /** --------- Добавить пользователя --------- */
    suspend fun getRetrofitAddNewUser(
        url: String,
        mCommand: String,
        hash: String,
        loginUser: String,
        passUser: String,
        typeUser: Int,
        nameUser: String
    ) = getListenerRetrofitIml.getRetrofitAddNewUser(
        url,
        mCommand,
        hash,
        loginUser,
        passUser,
        typeUser,
        nameUser
    )

    /** --------- Синхронизация пользователя --------- */
    suspend fun getSynchroUser(
        url: String,
        mCommand: String,
        hash: String,
    ) = getListenerRetrofitIml.getRetrofitSynchroUser(url, mCommand, hash)


    /** --------- Получить все продукты из БД --------- */
    suspend fun getRetrofitAllProducts(
        url: String,
        mCommand: String
    ) = getListenerRetrofitIml.getRetrofitAllProduct(url, mCommand)


    /** --------- Получить все продукты из БД --------- */
    suspend fun getRetrofitAllProductsTutor(
        url: String,
        mCommand: String
    ) = getListenerRetrofitIml.getRetrofitAllProductTutor(url, mCommand)
}