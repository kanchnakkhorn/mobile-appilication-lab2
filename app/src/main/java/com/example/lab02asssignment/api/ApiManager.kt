package com.example.lab02asssignment.api

import com.example.lab02asssignment.api.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager private constructor() {
  companion object {
    val instance: ApiManager by lazy {
      ApiManager()
    }
  }

  private val retrofit = Retrofit.Builder()
    .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
  val service = retrofit.create(ApiService::class.java)
}