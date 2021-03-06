package com.freemovies.firetv.adapter

interface IDiffComparator<Item> {
    fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean
    fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean
}