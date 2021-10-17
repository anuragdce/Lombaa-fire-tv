package com.lombaa.firetv.ui

import com.lombaa.firetv.R
import com.lombaa.firetv.base.ui.DataBindingActivity
import com.lombaa.firetv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity<ActivityMainBinding>(R.layout.activity_main, "main") {

    override fun onBindContentView(binding: ActivityMainBinding) {
        binding.lifecycleOwner = this
    }
}