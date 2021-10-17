package com.freemovies.firetv.adapter

interface IAdapter<ItemType> {
    fun submitList(list: List<ItemType>?)
}
