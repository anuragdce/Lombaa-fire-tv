package com.lombaa.firetv.ui.history

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lombaa.firetv.base.databinding.BindingViewHolder
import com.lombaa.firetv.base.databinding.OnItemClickListener
import com.lombaa.firetv.base.view.layoutInflater
import com.lombaa.firetv.databinding.ItemHisortyBinding

internal class HistoryAdapter(private val listener: OnItemClickListener<HistoryItem>) :
  ListAdapter<HistoryItem, HistoryAdapter.HistoryItemViewHolder>(
    HistoryDiffCallback()
  ) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
    return HistoryItemViewHolder(ItemHisortyBinding.inflate(parent.layoutInflater, parent, false))
  }

  override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
    holder.bind {
      item = getItem(position)
    }
  }

  internal class HistoryItemViewHolder(binding: ItemHisortyBinding) : BindingViewHolder<ItemHisortyBinding>(binding)
}

internal class HistoryDiffCallback : DiffUtil.ItemCallback<HistoryItem>() {
  override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem) = oldItem == newItem
  override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem) = oldItem == newItem
}

