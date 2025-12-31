package com.example.lab02asssignment

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.lab02asssignment.api.model.Place
import com.example.lab02asssignment.databinding.AcitivityPlaceDetailBinding
import com.squareup.picasso.Picasso

class PlaceDetailActivity: AppCompatActivity() {

  private lateinit var binding: AcitivityPlaceDetailBinding

  @RequiresApi(Build.VERSION_CODES.TIRAMISU)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Setup UI
    binding = AcitivityPlaceDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val place = intent.getSerializableExtra("place", Place::class.java)
    Picasso.get().load(place?.imageUrl).into(binding.placeImage)
    binding.placeName.text = place?.name
  }
}