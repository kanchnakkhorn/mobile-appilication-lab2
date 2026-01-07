package com.example.lab02asssignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab02asssignment.api.model.Activity
import com.example.lab02asssignment.api.model.Place
import com.example.lab02asssignment.databinding.ViewHolderMyActivityBinding
import com.example.lab02asssignment.databinding.ViewHolderMyActivityGridBinding

private const val VIEW_TYPE_LIST = 0
private const val VIEW_TYPE_GRID = 1
class ActivitiesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataSet = emptyList<Activity>()
    private var displayData = emptyList<Activity>()
    private lateinit var onItemClickListener: (Activity, Int) -> Unit;
    private var isGrid = false

    fun setOnItemClickListener(listener: (Activity, Int) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(activities: List<Activity>) {
        dataSet = activities
        displayData = activities
    }

//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ActivityViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = ViewHolderMyActivityBinding.inflate(layoutInflater, parent, false)
//        return ActivityViewHolder(binding)
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        if (viewType == VIEW_TYPE_GRID) {
            val binding = ViewHolderMyActivityGridBinding.inflate(
                inflater,
                parent,
                false
            )
            return ActivityGridViewHolder(binding)
        } else {
            val binding = ViewHolderMyActivityBinding.inflate(
                inflater,
                parent,
                false
            )
            return ActivityViewHolder(binding)
        }
    }


//    override fun onBindViewHolder(
//        holder: ActivityViewHolder,
//        position: Int)
//    {
//        val activity = displayData[position]
//        holder.display(activity)
//
////        holder.itemView.setOnClickListener {
////            onItemClickListener.invoke(activity, position)
////        }
//    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int) {
        val activity = displayData[position]

        when (holder) {
            is ActivityViewHolder -> holder.display(activity)
            is ActivityGridViewHolder -> holder.display(activity)
        }
       holder.itemView.setOnClickListener {
            onItemClickListener.invoke(activity, position)
       }
    }

    override fun getItemCount(): Int {
        return displayData.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGrid) VIEW_TYPE_GRID else VIEW_TYPE_LIST
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

    fun setGridView(bool: Boolean) {
        isGrid = bool
        notifyDataSetChanged()
    }
}