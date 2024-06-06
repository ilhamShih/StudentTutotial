package com.student.tutorial.core.impl

import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.core.model.AllUsersData
import com.student.tutorial.core.model.AutData
import com.student.tutorial.core.model.DeleteProduct
import com.student.tutorial.core.model.ProductData
import com.student.tutorial.core.model.SynchroAutData
import com.student.tutorial.core.model.UserAddEditDelete
import com.student.tutorial.core.net.common.HttpClient.apiRetrofitServiceGson
import com.student.tutorial.core.net.ext.CallAwait.awaitResult
import com.student.tutorial.core.net.ext.Result
import com.student.tutorial.helper.util.UtilTool.genUrl
import com.student.tutorial.helper.util.UtilTool.genUrlPath

interface GetListenerRetrofitIml {

    /** --------- Авторизация --------- */
    suspend fun getRetrofitUserAut(
        url: String,
        mCommand: String,
        mLogin: String,
        mPass: String
    ): Result<AutData>? = apiRetrofitServiceGson(genUrl(url)).getAut(
        host = genUrlPath(url), command = mCommand,
        login = mLogin, pass = mPass
    )?.awaitResult()

    /** --------- Отправить данные в базу --------- */
    suspend fun getRetrofitAddProduct(
        url: String,
        mCommand: String,
        hash: String,
        nameKey: String,
        comment: String,
        docName: String,
        responsible: String
    ): Result<ProductData>? = apiRetrofitServiceGson(genUrl(url)).addProduct(
        host = genUrlPath(url),
        command = mCommand,
        hash = hash,
        nameKey = nameKey,
        comment = comment,
        docName = docName,
        responsible = responsible
    )?.awaitResult()



    /** --------- Удалить продукт из БД --------- */
    suspend fun getRetrofitDeleteProduct(
        url: String,
        mCommand: String,
        hash: String,
        nameKey: String
    ): Result<DeleteProduct>? = apiRetrofitServiceGson(genUrl(url)).getDeleteProducts(
        host = genUrlPath(url),
        command = mCommand,
        hash = hash,
        nameKey = nameKey
    )?.awaitResult()

    /** --------- Получить всех пользователей --------- */
    suspend fun getRetrofitAllUsers(
        url: String,
        mCommand: String,
        hash: String
    ): Result<AllUsersData>? = apiRetrofitServiceGson(genUrl(url)).getAllUsers(
        host = genUrlPath(url),
        command = mCommand,
        hash = hash
    )?.awaitResult()


    /** --------- Редактировать пользователя --------- */
    suspend fun getRetrofitUsersEdit(
        url: String,
        mCommand: String,
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String,
    ): Result<UserAddEditDelete>? = apiRetrofitServiceGson(genUrl(url)).getUserEdit(
        host = genUrlPath(url),
        command = mCommand,
        hash = hash,
        idUser = idUser,
        typeUser = typeUser,
        nameUser = nameUser,
        loginUser = loginUser,
        passUser = passUser,
    )?.awaitResult()

    /** --------- Удалить пользователя --------- */
    suspend fun getRetrofitDeleteUser(
        url: String,
        mCommand: String,
        hash: String,
        idUser: Int
    ): Result<UserAddEditDelete>? = apiRetrofitServiceGson(genUrl(url)).getDeleteUser(
        host = genUrlPath(url),
        command = mCommand,
        hash = hash,
        idUser = idUser
    )?.awaitResult()

    /** --------- Добавить пользователя --------- */
    suspend fun getRetrofitAddNewUser(
        url: String,
        mCommand: String,
        hash: String,
        loginUser: String,
        passUser: String,
        typeUser: Int,
        nameUser: String,
    ): Result<UserAddEditDelete>? = apiRetrofitServiceGson(genUrl(url)).getAddNewUser(
        host = genUrlPath(url),
        command = mCommand,
        hash = hash,
        loginUser = loginUser,
        passUser = passUser,
        typeUser = typeUser,
        nameUser = nameUser,
    )?.awaitResult()

    /** --------- Синхронизация пользователя --------- */
    suspend fun getRetrofitSynchroUser(
        url: String,
        mCommand: String,
        hash: String
    ): Result<SynchroAutData>? = apiRetrofitServiceGson(genUrl(url)).getSynchroUser(
        host = genUrlPath(url),
        command = mCommand,
        hash = hash
    )?.awaitResult()

    /** --------- Получить все продукты из БД --------- */
    suspend fun getRetrofitAllProduct(
        url: String,
        mCommand: String,
    ): Result<AllProductsData>? = apiRetrofitServiceGson(genUrl(url)).getAllProducts(
        host = genUrlPath(url),
        command = mCommand
    )?.awaitResult()

    /** --------- Получить все продукты из БД --------- */
    suspend fun getRetrofitAllProductTutor(
        url: String,
        mCommand: String,
    ): Result<AllProductsTutorData>? = apiRetrofitServiceGson(genUrl(url)).getAllProductsTutor(
        host = genUrlPath(url),
        command = mCommand
    )?.awaitResult()


}