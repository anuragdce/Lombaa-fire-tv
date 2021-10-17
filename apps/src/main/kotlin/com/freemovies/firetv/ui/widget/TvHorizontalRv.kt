package com.freemovies.firetv.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.leanback.widget.BaseGridView
import androidx.leanback.widget.HorizontalGridView
import com.freemovies.firetv.R

open class TvHorizontalRv @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : HorizontalGridView(context, attrs, defStyle) {

    init {
        windowAlignment = BaseGridView.WINDOW_ALIGN_BOTH_EDGE

        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.TvHorizontalRv, 0, 0
        )

        if (a.hasValue(R.styleable.TvHorizontalRv_windowAlignmentOffset)) {
            windowAlignmentOffset = a.getDimensionPixelOffset(
                R.styleable.TvHorizontalRv_windowAlignmentOffset,
                0
            )
        }
        if (a.hasValue(R.styleable.TvHorizontalRv_windowAlignmentOffsetPercent)) {
            windowAlignmentOffsetPercent = a.getFloat(
                R.styleable.TvHorizontalRv_windowAlignmentOffsetPercent,
                0f
            )
        }

        if (a.hasValue(R.styleable.TvHorizontalRv_itemAlignmentOffset)) {
            itemAlignmentOffset = a.getDimensionPixelOffset(
                R.styleable.TvHorizontalRv_itemAlignmentOffset,
                0
            )
        }
        if (a.hasValue(R.styleable.TvHorizontalRv_itemAlignmentOffsetPercent)) {
            itemAlignmentOffsetPercent = a.getFloat(
                R.styleable.TvHorizontalRv_itemAlignmentOffsetPercent,
                0f
            )
        }
        setItemSpacing(
            a.getDimensionPixelOffset(
                R.styleable.TvHorizontalRv_itemSpacing,
                0
            )
        )

        a.recycle()
    }
}