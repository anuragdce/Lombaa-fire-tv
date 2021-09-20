package com.lombaa.firetv.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_MENU
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.lombaa.firetv.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event != null) {
            when (event.keyCode) {
                KEYCODE_MENU, KEYCODE_DPAD_LEFT -> {
                    drawerLayout.open()
                    return true
                }
            }
        }

        return super.dispatchKeyEvent(event)
    }

    override fun onBackPressed() {
        if (drawerLayout.isOpen) {
            drawerLayout.close()
            return
        }
        super.onBackPressed()
    }
}