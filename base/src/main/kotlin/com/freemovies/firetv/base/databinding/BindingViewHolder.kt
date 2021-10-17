package com.freemovies.firetv.base.databinding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindingViewHolder<T : ViewDataBinding>(private val binding: T) : RecyclerView.ViewHolder(binding.root) {
    fun bind(config: T.() -> Unit) {
        binding.config()
        binding.executePendingBindings()
    }
}