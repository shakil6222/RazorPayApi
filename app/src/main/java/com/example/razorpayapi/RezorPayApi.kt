package com.example.razorpayapi

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RezorPayApi {

    @GET("customers")
    fun getCustemer(@HeaderMap header : HashMap<String,String>): Observable<UserResponceListModel>

    @POST("customers")
    fun postCustemer(@HeaderMap header :HashMap<String,String>,@Body post:PostDataModel):Observable<UserResponceListModel>

    @PUT("customers/{id}")
    fun putCustemerUpdate(@HeaderMap header: HashMap<String, String>, @Path("id") id:String, @Body custemerUpdateModel: CustemerUpdateModel):Observable<CustemerUpdateModel>

    companion object Factory {
        fun createRetrofit(): RezorPayApi {
            val retrofit = Retrofit.
            Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.razorpay.com/v1/") // Update base URL here
                .build()
                .create(RezorPayApi::class.java)
            return retrofit
        }
    }
}