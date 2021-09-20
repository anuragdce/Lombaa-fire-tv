package com.lombaa.firetv.ui

import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_MENU
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import com.lombaa.firetv.R
import com.lombaa.firetv.base.navigation.findNavHostController
import com.lombaa.firetv.base.ui.DataBindingActivity
import com.lombaa.firetv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity<ActivityMainBinding>(R.layout.activity_main, "main") {
    private lateinit var drawerLayout: DrawerLayout
    private val viewModel: MainViewModel by viewModels()

    override fun onBindContentView(binding: ActivityMainBinding) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        drawerLayout = binding.drawerLayout

        viewModel.viewModeType.observe(this) {
            drawerLayout.close()
            when (it) {
                ViewMode.Home -> findNavHostController(R.id.nav_host_fragment).navigate(R.id.action_to_HomeFragment)
                ViewMode.History -> findNavHostController(R.id.nav_host_fragment).navigate(R.id.action_to_HistoryFragment)
                ViewMode.ContactUs -> findNavHostController(R.id.nav_host_fragment).navigate(R.id.action_to_ContactFragment)
            }
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event != null) {
            when (event.keyCode) {
                KEYCODE_MENU, KeyEvent.KEYCODE_DPAD_LEFT -> {
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