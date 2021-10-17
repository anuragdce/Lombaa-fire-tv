package com.freemovies.firetv.ui

import com.freemovies.firetv.R
import com.freemovies.firetv.base.ui.DataBindingActivity
import com.freemovies.firetv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity<ActivityMainBinding>(R.layout.activity_main, "main") {

    override fun onBindContentView(binding: ActivityMainBinding) {
        binding.lifecycleOwner = this
    }
}