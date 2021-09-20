package com.lombaa.firetv.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lombaa.firetv.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewGoneAnimator(findViewById(R.id.nav_menu_fragment))
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event != null) {
            when (event.keyCode) {
                KEYCODE_DPAD_LEFT -> {
                    viewGoneAnimator(findViewById(R.id.nav_menu_fragment))
                    return true
                }
                KEYCODE_DPAD_RIGHT -> {
                    viewVisibleAnimator(findViewById(R.id.nav_menu_fragment))
                    return true
                }
            }
        }

        return super.dispatchKeyEvent(event)
    }

    private fun viewGoneAnimator(view: View) {
        view.animate()
            .alpha(0f)
            .setDuration(1000)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.GONE
                }
            })
    }

    private fun viewVisibleAnimator(view: View) {
        view.animate()
            .alpha(1f)
            .setDuration(1000)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.VISIBLE
                }
            })
    }
}