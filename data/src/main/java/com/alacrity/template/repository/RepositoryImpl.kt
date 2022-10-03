package com.alacrity.template.repository

import com.alacrity.template.api.Api
import com.alacrity.template.entity.NumberWithFact
import com.alacrity.template.exceptions.NoDataFromResponseException
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RepositoryImpl @Inject constructor(
    private val retrofit: Retrofit,
) : Repository {

    override suspend fun getFact(number: Int): NumberWithFact {
        val api = retrofit.create(Api::class.java)
        val call = api.getFactAboutNumber(number)
        return suspendCoroutine { continuation ->
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val data = response.body()?.string()
                    data ?: continuation.resumeWithException(NoDataFromResponseException())
                        .also { return }

                    val uid = UUID.randomUUID().toString()
                    continuation.resume(
                        NumberWithFact(uid, number, data!!)
                    )
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}