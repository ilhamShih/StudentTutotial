package com.student.tutorial.core.domain

import com.student.tutorial.core.impl.GetListenerApi

class GetProductImpUseCase(private val getListenerApi: GetListenerApi) {

    /** --------- Отправить данные в базу --------- */
    suspend fun getProductAdd(
        hash: String,
        nameKey: String,
        comment: String,
        docName: String,
        responsible: String
    ) = getListenerApi.getProductAdd(hash, nameKey, comment, docName, responsible)


    /** --------- Удалить продукт из БД --------- */
    suspend fun getDeleteProducts(hash: String, nameKey: String) =
        getListenerApi.getDeleteProduct(hash, nameKey)

    /** --------- Получить все продукты из БД --------- */
    suspend fun getAllProducts() = getListenerApi.getAllProducts()

    /** --------- Получить все продукты из БД --------- */
    suspend fun getAllProductsTutor() = getListenerApi.getAllProductsTutor()

}