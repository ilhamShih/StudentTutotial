package com.student.tutorial.presentation.action.adapter.users

import com.student.tutorial.core.model.AllUsersData
import com.student.tutorial.presentation.action.common.TypeClick

interface OnClickAdapterUsers {
    fun onClickUsers(them: AllUsersData.Users, typeClick: TypeClick) {
    }
}