package com.example.lab02asssignment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.lab02asssignment.api.model.Activity
import com.example.lab02asssignment.databinding.ViewHolderMyActivityBinding
import com.squareup.picasso.Picasso

class ActivityViewHolder(val binding: ViewHolderMyActivityBinding): RecyclerView.ViewHolder(
    binding.root
) {
    fun display(activity: Activity) {
        binding.activityDate.text = activity.date
        binding.activityName.text = activity.title
        Picasso.get().load(activity.imageUrl).into(binding.activityImage)
    }
}