package com.student.tutorial.core.net.common

import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.core.model.AllUsersData
import com.student.tutorial.core.model.AutData
import com.student.tutorial.core.model.DeleteProduct
import com.student.tutorial.core.model.ProductData
import com.student.tutorial.core.model.SynchroAutData
import com.student.tutorial.core.model.UserAddEditDelete
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HttpClientInterface {

    /** --------- Авторизация --------- */
    @GET("{host}")
    fun getAut(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String,
        @Query(value = "login") login: String,
        @Query(value = "pass") pass: String
    ): Call<AutData>?

    /** --------- Отправить данные в базу --------- */
    @GET("{host}")
    fun addProduct(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String,
        @Query(value = "hash") hash: String,
        @Query(value = "name_key") nameKey: String,
        @Query(value = "comment") comment: String,
        @Query(value = "doc_name") docName: String,
        @Query(value = "responsible") responsible: String,
    ): Call<ProductData>?

    /** --------- Удалить продукт из БД --------- */
    @GET("{host}")
    fun getDeleteProducts(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String,
        @Query(value = "hash") hash: String,
        @Query(value = "name_key") nameKey: String
    ): Call<DeleteProduct>?

    /** --------- Получить всех пользователей --------- */
    @GET("{host}")
    fun getAllUsers(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String,
        @Query(value = "hash") hash: String
    ): Call<AllUsersData>?

    /** --------- Редактировать пользователя --------- */
    @GET("{host}")
    fun getUserEdit(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String,
        @Query(value = "hash") hash: String,
        @Query(value = "id") idUser: Int,
        @Query(value = "type") typeUser: Int,
        @Query(value = "name") nameUser: String,
        @Query(value = "login") loginUser: String,
        @Query(value = "pass") passUser: String,
    ): Call<UserAddEditDelete>?

    /** --------- Удалить пользователя --------- */
    @GET("{host}")
    fun getDeleteUser(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String,
        @Query(value = "hash") hash: String,
        @Query(value = "id") idUser: Int
    ): Call<UserAddEditDelete>?

    /** --------- Добавить пользователя --------- */
    @GET("{host}")
    fun getAddNewUser(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String,
        @Query(value = "hash") hash: String,
        @Query(value = "login") loginUser: String,
        @Query(value = "pass") passUser: String,
        @Query(value = "type") typeUser: Int,
        @Query(value = "name") nameUser: String,
    ): Call<UserAddEditDelete>?

    /** --------- Синхронизация пользователя --------- */
    @GET("{host}")
    fun getSynchroUser(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String,
        @Query(value = "hash") hash: String
    ): Call<SynchroAutData>?

    /** --------- Получить все протукты из БД --------- */
    @GET("{host}")
    fun getAllProducts(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String
    ): Call<AllProductsData>?

    /** --------- Получить все протукты из БД --------- */
    @GET("{host}")
    fun getAllProductsTutor(
        @Path(value = "host", encoded = true) host: String,
        @Query(value = "command") command: String
    ): Call<AllProductsTutorData>?


}