package com.freemovies.firetv.adapter

import com.freemovies.firetv.R
import com.freemovies.firetv.data.local.model.Movie
import com.freemovies.firetv.databinding.ItemPortraitBinding

class PortraitAdapter(
    carouselContract: CarouselContract,
    carouselPosition: Int
) : CarouselAdapter<Movie, ItemPortraitBinding>(
    carouselContract,
    CarouselDiffComparator(),
    carouselPosition
) {
    override fun getLayoutRes(viewType: Int) = R.layout.item_portrait

    override fun onBindViewHolder(holder: BaseVh<ItemPortraitBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.item = getItem(position)
    }
}
