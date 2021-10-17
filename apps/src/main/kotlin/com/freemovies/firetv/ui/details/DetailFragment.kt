package com.freemovies.firetv.ui.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.freemovies.firetv.R
import com.freemovies.firetv.base.ui.DataBindingFragment
import com.freemovies.firetv.databinding.FragmentDetailBinding
import com.freemovies.firetv.ui.player.PlaybackActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class DetailFragment :
    DataBindingFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onBindView(binding: FragmentDetailBinding) {
        binding.viewModel = viewModel
        viewModel.loadDetails(args.movieId)
        binding.btnPlay.requestFocus()
        binding.btnPlay.setOnClickListener {
            activity?.let {
                startActivity(PlaybackActivity.createIntent(it, args.movieId))
            }
        }
    }
}
