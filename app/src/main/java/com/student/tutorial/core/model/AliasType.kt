package com.student.tutorial.core.model

import kotlinx.coroutines.flow.Flow

typealias flowUserAut = Flow<ResultContainer<AutData?>>
typealias flowProduct = Flow<ResultContainer<ProductData?>>
typealias flowDeleteProduct = Flow<ResultContainer<DeleteProduct?>>
typealias flowAllUsers = Flow<ResultContainer<AllUsersData?>>
typealias flowEditDeleteUsers = Flow<ResultContainer<UserAddEditDelete?>>
typealias flowSynchro = Flow<ResultContainer<SynchroAutData?>>

typealias flowAllProduct = Flow<ResultContainer<AllProductsData?>>
typealias flowAllProductTutor = Flow<ResultContainer<AllProductsTutorData?>>

