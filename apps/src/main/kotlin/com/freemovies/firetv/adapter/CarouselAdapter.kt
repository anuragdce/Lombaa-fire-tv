package com.freemovies.firetv.adapter

import android.view.KeyEvent
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.freemovies.firetv.data.local.model.BaseContentItem
import com.freemovies.firetv.ext.isBackBtn

abstract class CarouselAdapter<ItemType : BaseContentItem, BindingType : ViewDataBinding>(
    protected val carouselContract: CarouselContract,
    diffComparator: IDiffComparator<ItemType>,
    var carouselPosition: Int
) : BaseRvAdapter<ItemType, BindingType>(diffComparator), CarouselContract {
    protected var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: BaseVh<BindingType>, position: Int) {
        val item = getItem(position)

        holder.binding.root.setOnFocusChangeListener { view, hasFocus ->
            onItemFocusChanged(view, hasFocus, position, carouselPosition, item)
        }

        holder.binding.root.setOnClickListener {
            onItemClick(carouselPosition, position, item)
        }

        holder.binding.root.setOnKeyListener { view, keyCode, keyEvent ->
            return@setOnKeyListener handleKeyEvent(
                recyclerView,
                view,
                keyCode,
                keyEvent,
                position,
                carouselPosition
            )
        }
    }

    override fun onItemClick(rowPosition: Int, itemPosition: Int, item: BaseContentItem) {
        carouselContract.onItemClick(rowPosition, itemPosition, item)
    }

    override fun handleKeyEvent(
        recycler: RecyclerView?,
        view: View,
        keyCode: Int,
        keyEvent: KeyEvent,
        itemPosition: Int,
        rowPosition: Int
    ): Boolean {
        if (keyEvent.isBackBtn() && itemPosition != 0) {
            recycler?.scrollToPosition(0)
            return true
        }

        return carouselContract.handleKeyEvent(
            recycler,
            view,
            keyCode,
            keyEvent,
            itemPosition,
            rowPosition
        )
    }

    override fun onItemFocusChanged(
        view: View,
        hasFocus: Boolean,
        itemPosition: Int,
        rowPosition: Int,
        item: BaseContentItem
    ) {
        carouselContract.onItemFocusChanged(view, hasFocus, itemPosition, rowPosition, item)
    }
}
