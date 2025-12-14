package com.example.lab02asssignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab02asssignment.api.model.Activity
import com.example.lab02asssignment.databinding.ViewHolderMyActivityBinding

class ActivitiesAdapter: RecyclerView.Adapter<ActivityViewHolder>() {
    var dataSet = emptyList<Activity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMyActivityBinding.inflate(layoutInflater, parent, false)
        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ActivityViewHolder,
        position: Int)
    {
        val activity = dataSet[position]
        holder.display(activity)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}