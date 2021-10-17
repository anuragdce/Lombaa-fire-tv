package com.freemovies.firetv.databinding

import android.view.View
import androidx.databinding.BindingAdapter

object BaseAdapter {
    @JvmStatic
    @BindingAdapter("bindVisibleGone")
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}