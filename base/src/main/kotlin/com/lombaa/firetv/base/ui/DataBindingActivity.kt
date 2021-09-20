package com.lombaa.firetv.base.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DataBindingActivity<B : ViewDataBinding>(
  @LayoutRes val layoutRes: Int,
  private val screenName: String
) : AppCompatActivity() {
  protected val TAG = javaClass.simpleName

  fun bindContentView(): B {
    return DataBindingUtil.setContentView(this, layoutRes)
  }

  protected abstract fun onBindContentView(binding: B)

  override fun onCreate(bundle: Bundle?) {
    super.onCreate(bundle)
    val binding = bindContentView()
    binding.lifecycleOwner = this
    onBindContentView(binding)
    updateStatusNavBar()
  }

  protected fun makeStatusBarNotTransparent() {
    getWindow().getDecorView()
      .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_VISIBLE)
    getWindow().setStatusBarColor(Color.BLACK)
  }

  protected fun makeStatusBarTransparent() {
    getWindow().getDecorView()
      .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    window.statusBarColor = Color.TRANSPARENT
  }

  private fun updateStatusNavBar() {
    window.navigationBarColor = Color.BLACK
  }
}
