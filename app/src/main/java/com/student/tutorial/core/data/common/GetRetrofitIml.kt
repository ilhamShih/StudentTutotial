package com.student.tutorial.core.data.common

import com.student.tutorial.core.impl.GetListenerRetrofitIml
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.core.model.AllUsersData
import com.student.tutorial.core.model.AutData
import com.student.tutorial.core.model.DeleteProduct
import com.student.tutorial.core.model.ProductData
import com.student.tutorial.core.model.SynchroAutData
import com.student.tutorial.core.model.UserAddEditDelete
import com.student.tutorial.core.net.ext.Result

object GetRetrofitIml : GetListenerRetrofitIml {
    /** --------- Авторизация --------- */
    override suspend fun getRetrofitUserAut(
        url: String,
        mCommand: String,
        mLogin: String,
        mPass: String
    ): Result<AutData>? {
        return super.getRetrofitUserAut(url, mCommand, mLogin, mPass)
    }

    /** --------- Отправить данные в базу --------- */
    override suspend fun getRetrofitAddProduct(
        url: String,
        mCommand: String,
        hash: String,
        nameKey: String,
        comment: String,
        docName: String,
        responsible: String
    ): Result<ProductData>? {
        return super.getRetrofitAddProduct(
            url,
            mCommand,
            hash,
            nameKey,
            comment,
            docName,
            responsible
        )
    }


    /** --------- Удалить продукт из БД --------- */
    override suspend fun getRetrofitDeleteProduct(
        url: String,
        mCommand: String,
        hash: String,
        nameKey: String
    ): Result<DeleteProduct>? {
        return super.getRetrofitDeleteProduct(url, mCommand, hash, nameKey)
    }

    /** --------- Получить всех пользователей --------- */
    override suspend fun getRetrofitAllUsers(
        url: String,
        mCommand: String,
        hash: String
    ): Result<AllUsersData>? {
        return super.getRetrofitAllUsers(url, mCommand, hash)
    }

    /** --------- Редактировать пользователя --------- */
    override suspend fun getRetrofitUsersEdit(
        url: String,
        mCommand: String,
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String
    ): Result<UserAddEditDelete>? {
        return super.getRetrofitUsersEdit(
            url,
            mCommand,
            hash,
            idUser,
            typeUser,
            nameUser,
            loginUser,
            passUser
        )
    }

    /** --------- Удалить пользователя --------- */
    override suspend fun getRetrofitDeleteUser(
        url: String,
        mCommand: String,
        hash: String,
        idUser: Int
    ): Result<UserAddEditDelete>? {
        return super.getRetrofitDeleteUser(url, mCommand, hash, idUser)
    }

    /** --------- Добавить пользователя --------- */
    override suspend fun getRetrofitAddNewUser(
        url: String,
        mCommand: String,
        hash: String,
        loginUser: String,
        passUser: String,
        typeUser: Int,
        nameUser: String
    ): Result<UserAddEditDelete>? {
        return super.getRetrofitAddNewUser(
            url,
            mCommand,
            hash,
            loginUser,
            passUser,
            typeUser,
            nameUser
        )
    }

    /** --------- Синхронизация пользователя --------- */
    override suspend fun getRetrofitSynchroUser(
        url: String,
        mCommand: String,
        hash: String
    ): Result<SynchroAutData>? {
        return super.getRetrofitSynchroUser(url, mCommand, hash)
    }

    override suspend fun getRetrofitAllProduct(
        url: String,
        mCommand: String
    ): Result<AllProductsData>? {
        return super.getRetrofitAllProduct(url, mCommand)
    }

    override suspend fun getRetrofitAllProductTutor(
        url: String,
        mCommand: String
    ): Result<AllProductsTutorData>? {
        return super.getRetrofitAllProductTutor(url, mCommand)
    }

}