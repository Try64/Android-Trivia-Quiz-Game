package com.airwrk.androidtriviaquizgame.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airwrk.androidtriviaquizgame.databinding.HistoryItemBinding
import com.airwrk.androidtriviaquizgame.extentions.ExtWithEachClass.getDate
import com.airwrk.androidtriviaquizgame.model.History

class HistoryAdapter(
    private var historyList: ArrayList<History>,
    private val listener: OnHistoryClickListener
):RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    inner class HistoryViewHolder(itemView: HistoryItemBinding) : RecyclerView.ViewHolder(itemView.root),
        View.OnClickListener {
        val dateTime = itemView.dateTime
        val serial = itemView.serialNo
        val score = itemView.score
        init {
            itemView.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onHistoryItemClick(position)
            }
        }

    }
    fun interface OnHistoryClickListener {
        fun onHistoryItemClick(position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemBinding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HistoryViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]
        holder.score.text = "Score: ${currentItem.score}"
        holder.dateTime.text = "Time: ${currentItem.date}"
        holder.dateTime.text = "Time: ${"dd/MM/yyyy hh:mm a".getDate(currentItem.date.toLong())}"
        holder.serial.text = "#${position+1}"
    }
}