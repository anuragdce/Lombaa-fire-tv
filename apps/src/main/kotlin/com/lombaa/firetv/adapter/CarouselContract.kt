package com.lombaa.firetv.adapter

import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lombaa.firetv.data.local.model.BaseContentItem

interface CarouselContract {
    fun onItemClick(
        rowPosition: Int,
        itemPosition: Int,
        item: BaseContentItem
    )

    fun handleKeyEvent(
        recycler: RecyclerView?,
        view: View,
        keyCode: Int,
        keyEvent: KeyEvent,
        itemPosition: Int,
        rowPosition: Int
    ): Boolean

    fun onItemFocusChanged(
        view: View,
        hasFocus: Boolean,
        itemPosition: Int,
        rowPosition: Int,
        item: BaseContentItem
    )
}