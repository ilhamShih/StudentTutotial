package com.student.tutorial.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.tutorial.core.data.common.GetBaseApi
import com.student.tutorial.core.domain.GetAuthorizationImpUseCase
import com.student.tutorial.core.domain.GetProductImpUseCase
import com.student.tutorial.core.domain.GetUsersImplUseCase
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.core.model.AllUsersData
import com.student.tutorial.core.model.AutData
import com.student.tutorial.core.model.DeleteProduct
import com.student.tutorial.core.model.ProductData
import com.student.tutorial.core.model.ResultContainer
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.core.model.SynchroAutData
import com.student.tutorial.core.model.UserAddEditDelete
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommonViewModel : ViewModel() {

    private val getAuthorizationImpUseCase = GetAuthorizationImpUseCase(GetBaseApi)
    private val getProductImpUseCase = GetProductImpUseCase(GetBaseApi)
    private val getUsersImplUseCase = GetUsersImplUseCase(GetBaseApi)

    private val _autLiveData = MutableLiveData<Boolean>()
    val autLiveData: LiveData<Boolean> = _autLiveData

    fun setAutLiveData(boolean: Boolean) {
        _autLiveData.value = boolean
        viewModelScope.launch {

        }

    }


    /** --------- Авторизация --------- */
    suspend fun getAutUser(login: String, pass: String) =
        withContext(Dispatchers.IO) {
            val userFlow = getAuthorizationImpUseCase.getAutUser(login, pass)
            var autData: AutData? = null
            userFlow.collect { result ->

                autData = when (result) {
                    is ResultContainer.ResultLoadContainer -> AutData(resultHttp = ResultHttp.LOADING)
                    is ResultContainer.ResultOkContainer -> {

                        AutData(
                            result = result.values?.result,
                            user = result.values?.user,
                            resultHttp = ResultHttp.OK
                        )
                    }

                    is ResultContainer.ResultErrorContainer -> AutData(resultHttp = ResultHttp.ERROR)
                    is ResultContainer.ResultExceptionContainer -> AutData(resultHttp = ResultHttp.EXCEPTION)
                    is ResultContainer.ResultNotContainer -> AutData(resultHttp = ResultHttp.ERROR)
                }
            }
            autData
        }

    /** --------- Отправить данные в базу --------- */
    suspend fun addProductAndUpdate(
        hash: String,
        nameKey: String,
        comment: String,
        docName: String,
        responsible: String
    ) = withContext(Dispatchers.IO) {
        val flow =
            getProductImpUseCase.getProductAdd(hash, nameKey, comment, docName, responsible)
        var productData: ProductData? = null
        flow.collect { result ->

            productData = when (result) {
                is ResultContainer.ResultLoadContainer -> ProductData(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    ProductData(
                        result = result.values?.result,
                        message = result.values?.message,
                        resultHttp = ResultHttp.OK
                    )
                }

                is ResultContainer.ResultErrorContainer -> ProductData(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> ProductData(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> ProductData(resultHttp = ResultHttp.ERROR)
            }
        }
        productData
    }

    /** --------- Получить все продукты из БД --------- */
    suspend fun getAllProducts() = withContext(Dispatchers.IO) {
        val flow =
            getProductImpUseCase.getAllProducts()
        var allProductsData: AllProductsData? = null
        flow.collect { result ->

            allProductsData = when (result) {
                is ResultContainer.ResultLoadContainer -> AllProductsData(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    AllProductsData(
                        result = result.values?.result,
                        message = result.values?.message,
                        product = result.values?.product,
                        resultHttp = ResultHttp.OK
                    )
                }

                is ResultContainer.ResultErrorContainer -> AllProductsData(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> AllProductsData(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> AllProductsData(resultHttp = ResultHttp.ERROR)
            }
        }
        allProductsData
    }

    /** --------- Удалить продукт из БД --------- */
    suspend fun getDeleteProducts(hash: String, nameKey: String) = withContext(Dispatchers.IO) {
        val flow =
            getProductImpUseCase.getDeleteProducts(hash, nameKey)
        var deleteProduct: DeleteProduct? = null
        flow.collect { result ->
            deleteProduct = when (result) {
                is ResultContainer.ResultLoadContainer -> DeleteProduct(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    DeleteProduct(
                        result = result.values?.result,
                        message = result.values?.message,
                        resultHttp = ResultHttp.OK
                    )
                }

                is ResultContainer.ResultErrorContainer -> DeleteProduct(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> DeleteProduct(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> DeleteProduct(resultHttp = ResultHttp.ERROR)
            }
        }
        deleteProduct
    }

    /** --------- Удалить продукт из БД --------- */
    suspend fun getDeleteUser(hash: String, idUser: Int) = withContext(Dispatchers.IO) {
        val flow =
            getUsersImplUseCase.getDeleteUser(hash, idUser)
        var deleteProduct: UserAddEditDelete? = null
        flow.collect { result ->
            deleteProduct = when (result) {
                is ResultContainer.ResultLoadContainer -> UserAddEditDelete(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    UserAddEditDelete(
                        result = result.values?.result,
                        message = result.values?.message,
                        resultHttp = ResultHttp.OK
                    )
                }

                is ResultContainer.ResultErrorContainer -> UserAddEditDelete(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> UserAddEditDelete(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> UserAddEditDelete(resultHttp = ResultHttp.ERROR)
            }
        }
        deleteProduct
    }

    /** --------- Получить все продукты из БД --------- */
    suspend fun getAllUsers(hash: String) = withContext(Dispatchers.IO) {
        val flow =
            getUsersImplUseCase.getAllUsers(hash)
        var allUsersData: AllUsersData? = null
        flow.collect { result ->

            allUsersData = when (result) {
                is ResultContainer.ResultLoadContainer -> AllUsersData(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    AllUsersData(
                        result = result.values?.result,
                        message = result.values?.message,
                        users = result.values?.users,
                        resultHttp = ResultHttp.OK
                    )
                }

                is ResultContainer.ResultErrorContainer -> AllUsersData(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> AllUsersData(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> AllUsersData(resultHttp = ResultHttp.ERROR)
            }
        }
        allUsersData
    }

    /** --------- Получить все продукты из БД --------- */
    suspend fun getUserUpdate(
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String,
    ) = withContext(Dispatchers.IO) {
        val flow =
            getUsersImplUseCase.getUserEdit(hash, idUser, typeUser, nameUser, loginUser, passUser)
        var allUsersData: UserAddEditDelete? = null
        flow.collect { result ->

            allUsersData = when (result) {
                is ResultContainer.ResultLoadContainer -> UserAddEditDelete(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    UserAddEditDelete(
                        result = result.values?.result,
                        message = result.values?.message,
                        resultHttp = ResultHttp.OK
                    )
                }

                is ResultContainer.ResultErrorContainer -> UserAddEditDelete(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> UserAddEditDelete(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> UserAddEditDelete(resultHttp = ResultHttp.ERROR)
            }
        }
        allUsersData
    }


    /** --------- Получить все продукты из БД --------- */
    suspend fun getSynchroUser(
        hash: String
    ) = withContext(Dispatchers.IO) {
        val flow =
            getAuthorizationImpUseCase.getSynchroUser(hash)
        var allUsersData: SynchroAutData? = null
        flow.collect { result ->

            allUsersData = when (result) {
                is ResultContainer.ResultLoadContainer -> SynchroAutData(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    SynchroAutData(
                        result = result.values?.result,
                        message = result.values?.message,
                        available = result.values?.available,
                        userType = result.values?.userType,
                        resultHttp = ResultHttp.OK
                    )
                }

                is ResultContainer.ResultErrorContainer -> SynchroAutData(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> SynchroAutData(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> SynchroAutData(resultHttp = ResultHttp.ERROR)
            }
        }
        allUsersData
    }


    /** --------- Получить все продукты из БД --------- */
    suspend fun getAddNewUser(
        hash: String,
        loginUser: String,
        passUser: String,
        typeUser: Int,
        nameUser: String
    ) = withContext(Dispatchers.IO) {
        val flow =
            getUsersImplUseCase.getAddNewUser(hash, loginUser, passUser, typeUser, nameUser)
        var allUsersData: UserAddEditDelete? = null
        flow.collect { result ->

            allUsersData = when (result) {
                is ResultContainer.ResultLoadContainer -> UserAddEditDelete(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    UserAddEditDelete(
                        result = result.values?.result,
                        message = result.values?.message,
                        resultHttp = ResultHttp.OK
                    )
                }

                is ResultContainer.ResultErrorContainer -> UserAddEditDelete(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> UserAddEditDelete(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> UserAddEditDelete(resultHttp = ResultHttp.ERROR)
            }
        }
        allUsersData
    }


    /** --------- Получить все продукты из БД --------- */
    suspend fun getAllProductsTutorials() = withContext(Dispatchers.IO) {
        val flow =
            getProductImpUseCase.getAllProductsTutor()
        var allProductsData: AllProductsTutorData? = null
        flow.collect { result ->
            allProductsData = when (result) {
                is ResultContainer.ResultLoadContainer -> AllProductsTutorData(resultHttp = ResultHttp.LOADING)
                is ResultContainer.ResultOkContainer -> {
                    AllProductsTutorData(
                        result = result.values?.result,
                        message = result.values?.message,
                        product = result.values?.product,
                        resultHttp = ResultHttp.OK
                    )
                }
                is ResultContainer.ResultErrorContainer -> AllProductsTutorData(resultHttp = ResultHttp.ERROR)
                is ResultContainer.ResultExceptionContainer -> AllProductsTutorData(resultHttp = ResultHttp.EXCEPTION)
                is ResultContainer.ResultNotContainer -> AllProductsTutorData(resultHttp = ResultHttp.ERROR)
            }
        }
        allProductsData
    }

}