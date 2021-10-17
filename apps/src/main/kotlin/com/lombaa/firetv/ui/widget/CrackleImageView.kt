package com.lombaa.firetv.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lombaa.firetv.R

class CrackleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private fun getLayoutRes() = R.layout.view_image_text

    private val textView: TextView
    private val imageView: ImageView
    private val scaleType: ImageView.ScaleType

    companion object {
        private const val ALPHA_ERROR = 0.25f
    }

    init {
        inflate(context, getLayoutRes(), this)
        textView = findViewById(R.id.text)
        imageView = findViewById(R.id.image)
        scaleType = imageView.scaleType
    }

    fun setErrorText(error: String?) {
        textView.text = error
    }

    fun loadUrl(url: String?) {
        imageView.scaleType = scaleType
        imageView.alpha = 1f
        textView.visibility = View.GONE

        Glide.with(imageView.context).load(url).error(R.drawable.logo)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.scaleType = ImageView.ScaleType.FIT_CENTER
                    imageView.alpha = ALPHA_ERROR
                    textView.visibility = View.VISIBLE
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(imageView)
    }
}