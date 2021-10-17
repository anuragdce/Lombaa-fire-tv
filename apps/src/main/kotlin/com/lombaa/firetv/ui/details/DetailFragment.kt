package com.lombaa.firetv.ui.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.lombaa.firetv.R
import com.lombaa.firetv.base.ui.DataBindingFragment
import com.lombaa.firetv.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class DetailFragment :
    DataBindingFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onBindView(binding: FragmentDetailBinding) {
        binding.viewModel = viewModel
        viewModel.loadDetails(args.movieId)
    }
}
