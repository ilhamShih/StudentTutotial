package com.student.tutorial.core.net.ext

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume

object CallAwait {

    suspend fun <T : Any> Call<T>.awaitResult(): Result<T> {
        return suspendCancellableCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    continuation.resumeWith(runCatching {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body == null) {
                                Result.Exception(NullPointerException("Response body is null"))
                            } else {
                                Result.Ok(body, response.raw())
                            }
                        } else {
                            Result.Error(HttpException(response), response.raw())
                        }
                    })
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    if (continuation.isCancelled) return
                    continuation.resume(Result.Exception(t))
                }
            })

            registerOnCompletion(continuation)
        }
    }

    private fun Call<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
        continuation.invokeOnCancellation {
            try {
                cancel()
            } catch (ex: Throwable) {
                //Ignore cancel exception
            }
        }
    }
}