package com.example.lab02asssignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab02asssignment.api.model.Place
import com.example.lab02asssignment.databinding.ViewHolderPlaceBinding

class FavoritePlacesAdapter: RecyclerView.Adapter<PlaceViewHolder>() {
  var dataSet = emptyList<Place>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): PlaceViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ViewHolderPlaceBinding.inflate(layoutInflater, parent, false)
    return PlaceViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: PlaceViewHolder,
    position: Int
  ) {
    val place = dataSet[position]
    holder.display(place)
  }

  override fun getItemCount(): Int {
    return dataSet.size
  }

}