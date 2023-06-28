package com.mbmdevelop.raulcome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mbmdevelop.raulcome.databinding.CellFeedInfoBinding
import com.mbmdevelop.raulcome.databinding.CellFoodTimingBinding
import java.text.SimpleDateFormat
import java.util.*

class FoodTimingAdapter(private val dataSet: List<FoodTiming>) :
        RecyclerView.Adapter<FoodTimingAdapter.ViewHolder>() {

        private lateinit var binding: CellFoodTimingBinding

        inner class ViewHolder(itemView : CellFoodTimingBinding) : RecyclerView.ViewHolder(itemView.root){
            fun bind(item : FoodTiming){
                binding.apply {
                    fillView(this, item)
                }
            }

            private fun fillView(binding: CellFoodTimingBinding, item: FoodTiming) {
                binding.dayDateValueTextView.text = item.date
                binding.startDateValueTextView.text = convertirTiempoALegible(item.timing)
            }

        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            binding = CellFoodTimingBinding.inflate(inflater,viewGroup,false)
            return ViewHolder(binding)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(dataSet[position])
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size
    }