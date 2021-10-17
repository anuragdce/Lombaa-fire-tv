package com.ade.crackle.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.freemovies.firetv.ui.widget.CrackleImageView

object ImageViewAdapter {
    @JvmStatic
    @BindingAdapter(value = ["bindUrl", "placeHolder"], requireAll = false)
    fun bindUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
        Glide.with(imageView).load(url).error(placeHolder).into(imageView)
    }

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "errorText"], requireAll = false)
    fun loadImage(crackleImageView: CrackleImageView, url: String?, error: String?) {
        crackleImageView.setErrorText(error)
        crackleImageView.loadUrl(url)
    }
}