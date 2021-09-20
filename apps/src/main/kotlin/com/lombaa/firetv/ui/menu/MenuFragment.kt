package com.lombaa.firetv.ui.menu

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.lombaa.firetv.R
import com.lombaa.firetv.base.ui.DataBindingFragment
import com.lombaa.firetv.databinding.FragmentMenuBinding
import com.lombaa.firetv.ui.MainViewModel
import com.lombaa.firetv.ui.ViewMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MenuFragment :
    DataBindingFragment<FragmentMenuBinding>(R.layout.fragment_menu) {
    private val viewModel: MenuViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onBindView(binding: FragmentMenuBinding) {
        binding.viewModel = viewModel

        binding.home.setOnClickListener {
            mainViewModel.setViewMode(ViewMode.Home)
        }

        binding.history.setOnClickListener {
            mainViewModel.setViewMode(ViewMode.History)
        }

        binding.contact.setOnClickListener {
            mainViewModel.setViewMode(ViewMode.ContactUs)
        }
    }
}