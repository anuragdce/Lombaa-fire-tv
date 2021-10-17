package com.lombaa.firetv.adapter

import com.lombaa.firetv.R
import com.lombaa.firetv.data.local.model.Movie
import com.lombaa.firetv.databinding.ItemPortraitBinding

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
