package com.student.tutorial.core.model

sealed class ResultContainer<out T> {
    data class ResultOkContainer<out T>(val values: T?) : ResultContainer<T>()
    data class ResultErrorContainer<out T>(val values: T?) : ResultContainer<T>()
    data class ResultExceptionContainer<out T>(val values: T?) : ResultContainer<T>()
    data class ResultLoadContainer<out T>(val values: T? = null) : ResultContainer<T>()
    data class ResultNotContainer<out T>(val values: T? = null) : ResultContainer<T>()
}
