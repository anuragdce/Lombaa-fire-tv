package com.freemovies.firetv.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.leanback.widget.BaseGridView
import androidx.leanback.widget.VerticalGridView
import com.freemovies.firetv.R

class TvVerticalRv @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : VerticalGridView(context, attrs, defStyle) {
    init {
        windowAlignment = BaseGridView.WINDOW_ALIGN_BOTH_EDGE

        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.TvVerticalRv, 0, 0
        )

        if (a.hasValue(R.styleable.TvVerticalRv_windowAlignmentOffset)) {
            windowAlignmentOffset = a.getDimensionPixelOffset(
                R.styleable.TvVerticalRv_windowAlignmentOffset,
                0
            )
        }
        if (a.hasValue(R.styleable.TvVerticalRv_windowAlignmentOffsetPercent)) {
            windowAlignmentOffsetPercent = a.getFloat(
                R.styleable.TvVerticalRv_windowAlignmentOffsetPercent,
                0f
            )
        }

        if (a.hasValue(R.styleable.TvVerticalRv_itemAlignmentOffset)) {
            itemAlignmentOffset = a.getDimensionPixelOffset(
                R.styleable.TvVerticalRv_itemAlignmentOffset,
                0
            )
        }
        if (a.hasValue(R.styleable.TvVerticalRv_itemAlignmentOffsetPercent)) {
            itemAlignmentOffsetPercent = a.getFloat(
                R.styleable.TvVerticalRv_itemAlignmentOffsetPercent,
                0f
            )
        }
        setItemSpacing(
            a.getDimensionPixelOffset(
                R.styleable.TvVerticalRv_itemSpacing,
                0
            )
        )

        a.recycle()
    }
}