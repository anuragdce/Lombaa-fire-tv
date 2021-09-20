package com.lombaa.firetv.ui.contact

import androidx.fragment.app.activityViewModels
import com.lombaa.firetv.R
import com.lombaa.firetv.base.ui.DataBindingFragment
import com.lombaa.firetv.databinding.FragmentContactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ContactFragment :
  DataBindingFragment<FragmentContactBinding>(R.layout.fragment_contact) {
  private val viewModel: ContactViewModel by activityViewModels()

  override fun onBindView(binding: FragmentContactBinding) {
    binding.viewModel = viewModel
  }
}