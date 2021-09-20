package com.lombaa.firetv.ui.history

import androidx.fragment.app.activityViewModels
import com.lombaa.firetv.R
import com.lombaa.firetv.base.ui.DataBindingFragment
import com.lombaa.firetv.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class HistoryFragment :
  DataBindingFragment<FragmentHistoryBinding>(R.layout.fragment_history) {
  private val viewModel: HistoryViewModel by activityViewModels()

  override fun onBindView(binding: FragmentHistoryBinding) {
    binding.viewModel = viewModel
    binding.list.adapter = HistoryAdapter {

    }
  }
}