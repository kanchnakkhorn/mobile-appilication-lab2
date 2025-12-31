package com.example.lab02asssignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab02asssignment.api.model.Place
import com.example.lab02asssignment.databinding.ViewHolderPlaceBinding

class FavoritePlacesAdapter: RecyclerView.Adapter<PlaceViewHolder>() {
  private var dataSet = emptyList<Place>()
  private var displayData = emptyList<Place>()

  private lateinit var onItemClickListener: (Place, Int) -> Unit

  fun setOnItemClickListener(listener: (Place, Int) -> Unit) {
    onItemClickListener = listener
  }

  fun setData(places: List<Place>) {
    dataSet = places
    displayData = places
  }

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
    val place = displayData[position]
    holder.display(place)

    holder.itemView.setOnClickListener {
      onItemClickListener.invoke(place, position)
    }
  }

  override fun getItemCount(): Int {
    return displayData.size
  }

  fun search(keyword: String) {
    displayData = dataSet.filter { place ->
      place.name.lowercase().contains(keyword.lowercase())
    }
    notifyDataSetChanged()
  }

}