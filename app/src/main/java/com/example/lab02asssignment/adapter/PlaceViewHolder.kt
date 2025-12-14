package com.example.lab02asssignment.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lab02asssignment.api.model.Place
import com.example.lab02asssignment.databinding.ViewHolderPlaceBinding
import com.squareup.picasso.Picasso

class PlaceViewHolder(val binding: ViewHolderPlaceBinding): RecyclerView.ViewHolder(
  binding.root
) {
  fun display(place: Place) {
    binding.imageName.text = place.name
    Picasso.get().load(place.imageUrl).into(binding.imageThumbnail)
  }
}