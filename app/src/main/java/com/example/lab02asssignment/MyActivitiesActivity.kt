package com.example.lab02asssignment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab02asssignment.adapter.ActivitiesAdapter
import com.example.lab02asssignment.api.model.Activity
import com.example.lab02asssignment.api.service.ApiService
import com.example.lab02asssignment.databinding.ActivityMyActivitiesBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyActivitiesActivity: AppCompatActivity() {
  private lateinit var binding: ActivityMyActivitiesBinding
  val adapter = ActivitiesAdapter()
  var isGridLayout = false
  var activitiesLayoutManager = LinearLayoutManager(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMyActivitiesBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.year1.setOnClickListener {
      adapter.filterByYear(1)
    }

    binding.year2.setOnClickListener {
      adapter.filterByYear(2)
    }

    binding.year3.setOnClickListener {
      adapter.filterByYear(3)
    }

    binding.year4.setOnClickListener {
//      adapter.filterByYear(4)
      isGridLayout = !isGridLayout

      if (isGridLayout) {
        activitiesLayoutManager = GridLayoutManager(this, 2)
      } else {
        activitiesLayoutManager = LinearLayoutManager(this)
      }
      loadAndDisplayActivities()
    }

    loadAndDisplayProfile()
    loadAndDisplayActivities()
  }

  private fun loadAndDisplayProfile() {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/final-2025/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    val apiService = retrofit.create(ApiService::class.java)
    GlobalScope.launch {
      try {
        val profile = apiService.getProfile()
        withContext(Dispatchers.Main) {
          binding.name.text = profile.fullName()
          Picasso.get().load(profile.profileImage).into(binding.imageProfile)
          Picasso.get().load(profile.coverImage).into(binding.imageCover)
        }
      } catch (err: Exception) {
        Log.d("[kanchnak]", "Load profile error: ${err.message}")
      } finally {
        binding.profileImageLoading.isVisible = false
      }
    }
  }

  private fun loadAndDisplayActivities() {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/final-2025/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    val apiService = retrofit.create(ApiService::class.java)
    GlobalScope.launch {
      val activities = apiService.getActivities()
      withContext(Dispatchers.Main) {
        displayActivities(activities)
      }
    }
  }

  private fun displayActivities(activities: List<Activity>) {
    Log.d("Display Activities", "${activities}")
    adapter.setData(activities)

    binding.rclActivities.adapter = adapter
    binding.rclActivities.layoutManager = activitiesLayoutManager

  }
}