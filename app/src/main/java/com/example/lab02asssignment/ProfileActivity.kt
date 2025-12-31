package com.example.lab02asssignment

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab02asssignment.adapter.FavoritePlacesAdapter
import com.example.lab02asssignment.api.ApiManager
import com.example.lab02asssignment.api.model.Place
import com.example.lab02asssignment.api.service.ApiService
import com.example.lab02asssignment.databinding.ActivityProfileBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileActivity : AppCompatActivity() {
  private val activityLauncher = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
  ) {
    result ->
    if (result.resultCode == RESULT_OK) {
      val newName = result.data?.getStringExtra("new-name")
      binding.profileName.text = newName
      binding.profileNameTop.text = newName
//      name = newName.toString()
    }
  }
  private lateinit var binding: ActivityProfileBinding
  private val adapter = FavoritePlacesAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.enableEdgeToEdge(window)

  // Set up UI
  // setContentView(R.layout.activity_profile) This is an old method
    binding = ActivityProfileBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Setup event listener
    binding.addStory.setOnClickListener { openSearchActivity() }
    binding.editProfileBig.setOnClickListener { openMyActivitiesAcitivity() }

    binding.search.doAfterTextChanged { edt ->
      val keyword = edt.toString()
      adapter.search(keyword)
    }

    loadAndDisplayProfile()
    loadAndDisplayFavoritePlaces()
  }

  private fun openSearchActivity() {
    val intent = Intent(this, SearchActivity::class.java)
    startActivity(intent)
  }

  private fun openMyActivitiesAcitivity() {
    val intent = Intent(this, MyActivitiesActivity::class.java)
    startActivity(intent)
  }

  private fun openEditProfileActivity() {
    val intent = Intent(this, EditProfileActivity::class.java)
//    intent.putExtra("name", name)
//    startActivity(intent)
    activityLauncher.launch(intent)
  }

  private fun loadAndDisplayProfile() {
//    binding.profileName.text = name
//    binding.profileNameTop.text = name

    // Load Data from API
//    val retrofit = Retrofit.Builder()
//      .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/api/")
//      .addConverterFactory(GsonConverterFactory.create())
//      .build()
//    val apiService = retrofit.create(ApiService::class.java)
    GlobalScope.launch {
      try {
        val profile = ApiManager.instance.service.getProfile()
        withContext(Dispatchers.Main) {
          binding.profileName.text = profile.fullName()

          Picasso.get().load(profile.profileImage).into(binding.imageProfile)
          Picasso.get().load(profile.coverImage).into(binding.imageCover)
          hideLoading()
        }
      } catch (err: Exception) {
        hideLoading()
        Log.d("[profile-activity]", "Load profile error: ${err.message}")
        showError(err)
      }
    }
  }

  private fun loadAndDisplayFavoritePlaces() {
  //    Load data from API
//    val retrofit = Retrofit.Builder()
//      .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/api/")
//      .addConverterFactory(GsonConverterFactory.create())
//      .build()
//    val apiService = retrofit.create(ApiService::class.java)
    GlobalScope.launch {
      val places = ApiManager.instance.service.getFavoritePlaces()
      withContext(Dispatchers.Main) {
        displayPlaces(places)
      }

    }
  }

  private fun displayPlaces(places: List<Place>) {
    Log.d("displayPlaces", "${places}")


    adapter.setOnItemClickListener { place, position ->
//      Toast.makeText(this@ProfileActivity, place.name, Toast.LENGTH_LONG).show()
      val intent = Intent(this@ProfileActivity, PlaceDetailActivity::class.java)
      intent.putExtra("place", place)
      startActivity(intent)
    }

    adapter.setData(places)
    binding.rclPlaces.adapter = adapter

    val layoutManager = LinearLayoutManager(this)
    binding.rclPlaces.layoutManager = layoutManager
  }

  private fun showLoading() {
    binding.profileImageLoading.isVisible = true
  }

  private fun hideLoading() {
    binding.profileImageLoading.isVisible = false
  }

  private fun showError(err: Exception) {}

}