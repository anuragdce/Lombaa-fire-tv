package com.lombaa.firetv.ui.menu

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.lombaa.firetv.R
import com.lombaa.firetv.base.asMutable
import com.lombaa.firetv.base.ui.DataBindingFragment
import com.lombaa.firetv.databinding.FragmentMenuBinding
import com.lombaa.firetv.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MenuFragment :
  DataBindingFragment<FragmentMenuBinding>(R.layout.fragment_menu) {
  private val viewModel: MenuViewModel by activityViewModels()
  private val mainViewModel: MainViewModel by viewModels()

  override fun onBindView(binding: FragmentMenuBinding) {
    binding.viewModel = viewModel

    binding.home.setOnClickListener {
      mainViewModel.navigateType.asMutable().value = MainViewModel.FragmentType.Home
    }

    binding.history.setOnClickListener {
      mainViewModel.navigateType.asMutable().value = MainViewModel.FragmentType.History
    }

    binding.contact.setOnClickListener {
      mainViewModel.navigateType.asMutable().value = MainViewModel.FragmentType.ContactUs
    }
  }
}