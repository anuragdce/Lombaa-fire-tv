package com.lombaa.firetv.ui.history

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("histories")
internal fun setHistories(recyclerView: RecyclerView, items: List<HistoryItem>?) {
  items?.let {
    (recyclerView.adapter as HistoryAdapter).submitList(items)
  }
}