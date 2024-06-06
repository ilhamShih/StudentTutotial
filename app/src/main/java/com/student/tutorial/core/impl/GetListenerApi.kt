package com.student.tutorial.core.impl

import com.student.tutorial.core.model.flowAllProduct
import com.student.tutorial.core.model.flowAllProductTutor
import com.student.tutorial.core.model.flowAllUsers
import com.student.tutorial.core.model.flowDeleteProduct
import com.student.tutorial.core.model.flowEditDeleteUsers
import com.student.tutorial.core.model.flowProduct
import com.student.tutorial.core.model.flowSynchro
import com.student.tutorial.core.model.flowUserAut

interface GetListenerApi {
    /** --------- Авторизуемся  --------- */
    suspend fun getUserAut(mLogin: String, mPass: String): flowUserAut

    /** --------- Отправить данные в базу --------- */
    suspend fun getProductAdd(
        hash: String,
        nameKey: String,
        comment: String,
        docName: String,
        responsible: String
    ): flowProduct

    /** --------- Получить все продукты из БД --------- */
    suspend fun getAllProducts(): flowAllProduct

    /** --------- Удалить продукт из БД --------- */
    suspend fun getDeleteProduct(hash: String, nameKey: String): flowDeleteProduct

    /** --------- Получить всех пользователей --------- */
    suspend fun getAllUsers(hash: String): flowAllUsers

    /** --------- Редактировать пользователя --------- */
    suspend fun getUserEdit(
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String,
    ): flowEditDeleteUsers

    /** --------- Удалить пользователя --------- */
    suspend fun getUserDelete(hash: String, idUser: Int): flowEditDeleteUsers

    /** --------- Добавить пользователя --------- */
    suspend fun getAddNewUser(
        hash: String,
        loginUser: String,
        passUser: String,
        typeUser: Int,
        nameUser: String
    ): flowEditDeleteUsers
    /** --------- Синхронизация пользователя --------- */
    suspend fun getSynchroUser(hash: String): flowSynchro

    /** --------- Получить все продукты из БД --------- */
    suspend fun getAllProductsTutor(): flowAllProductTutor
}
