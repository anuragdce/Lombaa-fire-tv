package com.lombaa.firetv.adapter

interface IAdapter<ItemType> {
    fun submitList(list: List<ItemType>?)
}
