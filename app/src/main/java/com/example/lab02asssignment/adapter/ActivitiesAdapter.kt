package com.example.lab02asssignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab02asssignment.api.model.Activity
import com.example.lab02asssignment.api.model.Place
import com.example.lab02asssignment.databinding.ViewHolderMyActivityBinding

class ActivitiesAdapter: RecyclerView.Adapter<ActivityViewHolder>() {
    private var dataSet = emptyList<Activity>()
    private var displayData = emptyList<Activity>()
    private lateinit var onItemClickListener: (Activity, Int) -> Unit;

//    fun setOnItemClickListener(listener: (Activity, Int) -> Unit) {
//        onItemClickListener = listener
//    }

    fun setData(activities: List<Activity>) {
        dataSet = activities
        displayData = activities
    }

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
        val activity = displayData[position]
        holder.display(activity)

//        holder.itemView.setOnClickListener {
//            onItemClickListener.invoke(activity, position)
//        }
    }

    override fun getItemCount(): Int {
        return displayData.size
    }

    fun filterByYear(year: Int) {

        displayData = dataSet.filter { activity ->
          activity.year == year
        }
        Log.d("FILTER YEAR", "${year}");
        Log.d("FILTER ORIGINAL DATA", "${dataSet}")
        Log.d("FILTER", "data: ${displayData}")
        notifyDataSetChanged()
    }
}