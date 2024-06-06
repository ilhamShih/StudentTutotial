package com.student.tutorial.core.data.common

import com.student.tutorial.core.data.aut.GetUserAut
import com.student.tutorial.core.data.product.GetAllProducts
import com.student.tutorial.core.data.product.GetAllProductsTutor
import com.student.tutorial.core.data.product.GetDeleteProduct
import com.student.tutorial.core.data.product.GetProductAdd
import com.student.tutorial.core.data.users.GetAddNewUser
import com.student.tutorial.core.data.users.GetAllUsers
import com.student.tutorial.core.data.users.GetSynchroUser
import com.student.tutorial.core.data.users.GetUserDelete
import com.student.tutorial.core.data.users.GetUserEdit
import com.student.tutorial.core.impl.GetListenerApi
import com.student.tutorial.core.model.flowAllProduct
import com.student.tutorial.core.model.flowAllProductTutor
import com.student.tutorial.core.model.flowAllUsers
import com.student.tutorial.core.model.flowDeleteProduct
import com.student.tutorial.core.model.flowEditDeleteUsers
import com.student.tutorial.core.model.flowProduct
import com.student.tutorial.core.model.flowSynchro
import com.student.tutorial.core.model.flowUserAut

object GetBaseApi : GetListenerApi {

    /** --------- Авторизация --------- */
    override suspend fun getUserAut(mLogin: String, mPass: String): flowUserAut =
        GetUserAut().getUserAutFlow(mLogin, mPass)

    /** --------- Отправить данные в базу --------- */
    override suspend fun getProductAdd(
        hash: String,
        nameKey: String,
        comment: String,
        docName: String,
        responsible: String
    ): flowProduct =
        GetProductAdd().getProductAddFlow(hash, nameKey, comment, docName, responsible)

    /** --------- Получить все продукты из БД --------- */
    override suspend fun getAllProducts(): flowAllProduct = GetAllProducts().getAllProductsFlow()

    /** --------- Удалить продукт из БД --------- */
    override suspend fun getDeleteProduct(hash: String, nameKey: String): flowDeleteProduct =
        GetDeleteProduct().getDeleteProductsFlow(hash, nameKey)



    /** --------- Редактировать пользователя --------- */
    override suspend fun getUserEdit(
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String
    ): flowEditDeleteUsers = GetUserEdit().getEditUserFlow(
        hash,
        idUser,
        typeUser,
        nameUser,
        loginUser,
        passUser
    )

    /** --------- Удалить пользователя --------- */
    override suspend fun getUserDelete(hash: String, idUser: Int): flowEditDeleteUsers =
        GetUserDelete().getDeleteUserFlow(hash, idUser)

    /** --------- Добавить пользователя --------- */
    override suspend fun getAddNewUser(
        hash: String,
        loginUser: String,
        passUser: String,
        typeUser: Int,
        nameUser: String
    ): flowEditDeleteUsers = GetAddNewUser().getAddNewUserFlow(
        hash,
        loginUser,
        passUser,
        typeUser,
        nameUser
    )

    /** --------- Получить всех пользователей --------- */
    override suspend fun getAllUsers(hash: String): flowAllUsers =
        GetAllUsers().getAllUsersFlow(hash)

    /** --------- Синхронизация пользователя --------- */
    override suspend fun getSynchroUser(hash: String): flowSynchro =
        GetSynchroUser().getSynchroUserFlow(hash)


    /** --------- Получить всех пользователей --------- */
    override suspend fun getAllProductsTutor(): flowAllProductTutor =
        GetAllProductsTutor().getAllProductsTutorFlow()





}