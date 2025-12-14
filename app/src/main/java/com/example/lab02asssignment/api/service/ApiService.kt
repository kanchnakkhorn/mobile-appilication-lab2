package com.example.lab02asssignment.api.service

import com.example.lab02asssignment.api.model.Activity
import com.example.lab02asssignment.api.model.Place
import com.example.lab02asssignment.api.model.Profile
import retrofit2.http.GET

interface ApiService {
  @GET("profile.json")
  suspend fun getProfile(): Profile

  @GET("places.json")
  suspend fun getFavoritePlaces(): List<Place>

  @GET("activities.json")
  suspend fun getActivities(): List<Activity>
}