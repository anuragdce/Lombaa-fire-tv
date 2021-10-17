package com.lombaa.firetv.adapter

import com.lombaa.firetv.data.local.model.BaseContentItem
import com.lombaa.firetv.data.local.model.Movie

class CarouselsDiffComparator : IDiffComparator<BaseContentItem> {
    override fun areItemsTheSame(oldItem: BaseContentItem, newItem: BaseContentItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: BaseContentItem, newItem: BaseContentItem): Boolean {
        return false
    }
}

class CarouselDiffComparator : IDiffComparator<Movie> {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}

