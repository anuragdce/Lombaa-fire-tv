package com.lombaa.firetv.ui.menu

import androidx.fragment.app.activityViewModels
import com.lombaa.firetv.R
import com.lombaa.firetv.base.ui.DataBindingFragment
import com.lombaa.firetv.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MenuFragment :
  DataBindingFragment<FragmentMenuBinding>(R.layout.fragment_menu) {
  private val viewModel: MenuViewModel by activityViewModels()

  override fun onBindView(binding: FragmentMenuBinding) {
    binding.viewModel = viewModel

    binding.home.setOnClickListener {

    }

    binding.history.setOnClickListener {

    }

    binding.contact.setOnClickListener {

    }
  }
}