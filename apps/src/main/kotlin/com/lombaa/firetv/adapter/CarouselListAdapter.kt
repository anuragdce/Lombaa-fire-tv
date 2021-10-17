package com.lombaa.firetv.adapter

import android.view.KeyEvent
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.leanback.widget.BaseGridView
import androidx.recyclerview.widget.RecyclerView
import com.lombaa.firetv.data.local.model.BaseContentItem
import com.lombaa.firetv.ext.isBackBtn

abstract class CarouselsListAdapter<BindingType : ViewDataBinding>(
    protected open val contract: CarouselContract,
    diffComparator: IDiffComparator<BaseContentItem>
) : BaseRvAdapter<BaseContentItem, BindingType>(diffComparator), CarouselContract {
    protected var recyclerView: RecyclerView? = null

    // should be used to save & restore carousel position
    protected val savedRowPositions = HashMap<Int, Int>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun handleKeyEvent(
        recycler: RecyclerView?,
        view: View,
        keyCode: Int,
        keyEvent: KeyEvent,
        itemPosition: Int,
        rowPosition: Int
    ): Boolean {
        // if back btn and we can scroll up - do it
        if (keyEvent.isBackBtn() && rowPosition != 0) {
            this.recyclerView?.scrollToPosition(0)
            return true
        }

        return contract.handleKeyEvent(recycler, view, keyCode, keyEvent, itemPosition, rowPosition)
    }

    override fun onItemFocusChanged(
        view: View,
        hasFocus: Boolean,
        itemPosition: Int,
        rowPosition: Int,
        item: BaseContentItem
    ) {
        contract.onItemFocusChanged(view, hasFocus, itemPosition, rowPosition, item)
        savedRowPositions[rowPosition] = itemPosition
    }

    override fun onItemClick(rowPosition: Int, itemPosition: Int, item: BaseContentItem) {
        contract.onItemClick(rowPosition, itemPosition, item)
    }

    protected fun scrollCarouselToSavedPosition(carousel: BaseGridView, rowKey: Int) {
        carousel.setSelectedPosition(savedRowPositions[rowKey] ?: 0, 0)
    }

    /**
     * This should be used if we are changing content and what to remove previous selection state
     */
    fun clearSavedRowPosition() {
        savedRowPositions.clear()
    }
}
