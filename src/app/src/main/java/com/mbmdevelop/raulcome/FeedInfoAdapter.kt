package com.mbmdevelop.raulcome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mbmdevelop.raulcome.databinding.CellFeedInfoBinding
import java.text.SimpleDateFormat
import java.util.*

class FeedInfoAdapter(private val dataSet: MutableList<FeedInfo>) :
    RecyclerView.Adapter<FeedInfoAdapter.ViewHolder>() {

    private lateinit var binding: CellFeedInfoBinding

    inner class ViewHolder(itemView : CellFeedInfoBinding) : RecyclerView.ViewHolder(itemView.root){
        fun bind(item : FeedInfo){
            binding.apply {
                fillView(this, item)
            }
        }

        private fun fillView(binding: CellFeedInfoBinding, item: FeedInfo) {
            val pattern = "HH:mm"
            val simpleDateFormat = SimpleDateFormat(pattern)

            item.startDate?.let {

                binding.startDateValueTextView.text = simpleDateFormat.format(it)
                binding.dayDateValueTextView.text = getDayLabel(it)
            }

            binding.durationLabelTextView.isVisible = false
            binding.endDateLabelTextView.isVisible = false
            item.endDate?.let {
                binding.durationLabelTextView.isVisible = true
                binding.endDateLabelTextView.isVisible = true
                var minutes = calculateMinutes(item.startDate!!, it)
                var minutesFromNow = calcularTiempoTranscurrido(it, Date())
                binding.endDateValueTextView.text = simpleDateFormat.format(it)
                binding.durationValueTextView.text = minutes.toString() + " minutos"
                binding.timePassedValueTextView.text = "Hace " +minutesFromNow //"Hace " + minutesFromNow.toString() + " minutos"
            }

            binding.boobLabelTextView.isVisible = false
            item.boob.let {
                binding.boobLabelTextView.isVisible = true
                binding.boobLabelTextView.text = getBoobTextByInt(it)
            }

            binding.removeBottom.isVisible = false
            binding.removeBottom.setOnClickListener{

            }
        }

        private fun getBoobTextByInt(it: Int): CharSequence {
            return when (it) {
                1 -> "IZQ"
                2 -> "DRCH"
                3 -> "Ambas"
                else -> ""
            }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater =LayoutInflater.from(viewGroup.context)
        binding = CellFeedInfoBinding.inflate(inflater,viewGroup,false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}