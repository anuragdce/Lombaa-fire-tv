package com.lombaa.firetv.ui.home

import androidx.fragment.app.activityViewModels
import com.lombaa.firetv.R
import com.lombaa.firetv.base.ui.DataBindingFragment
import com.lombaa.firetv.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class HomeFragment :
  DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  private val viewModel: HomeViewModel by activityViewModels()

  override fun onBindView(binding: FragmentHomeBinding) {
    binding.viewModel = viewModel
  }
}