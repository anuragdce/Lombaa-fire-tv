package com.lombaa.firetv.ui.home

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lombaa.firetv.R
import com.lombaa.firetv.adapter.*
import com.lombaa.firetv.data.local.model.PlayList
import com.lombaa.firetv.databinding.CarouselPortraitBinding

class HomeAdapter(
    override val contract: CarouselContract
) : CarouselsListAdapter<ViewDataBinding>(
    contract,
    CarouselsDiffComparator()
) {
    private val portraitViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVh<ViewDataBinding> {
        val vh = super.onCreateViewHolder(parent, viewType)
        when (val binding = vh.binding) {
            is CarouselPortraitBinding -> {
                binding.rv.setHasFixedSize(true)
                binding.rv.setRecycledViewPool(portraitViewPool)
                binding.rv.adapter = PortraitAdapter(this, 0)
            }
        }
        return vh
    }

    override fun onBindViewHolder(holder: BaseVh<ViewDataBinding>, position: Int) {
        when (val binding = holder.binding) {
            is CarouselPortraitBinding -> {
                val item = getItem(position) as PlayList
                binding.item = item
                (binding.rv.adapter as? PortraitAdapter)?.run {
                    carouselPosition = position
                    submitList(item.movies)
                }
            }
        }
    }

    override fun getLayoutRes(viewType: Int) = viewType

    override fun getItemViewType(position: Int): Int = R.layout.carousel_portrait
}