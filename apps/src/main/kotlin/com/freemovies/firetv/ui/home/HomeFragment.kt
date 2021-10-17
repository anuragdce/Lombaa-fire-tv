package com.freemovies.firetv.ui.home

import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.freemovies.firetv.R
import com.freemovies.firetv.adapter.CarouselContract
import com.freemovies.firetv.base.ui.DataBindingFragment
import com.freemovies.firetv.data.local.model.BaseContentItem
import com.freemovies.firetv.data.local.model.Movie
import com.freemovies.firetv.databinding.FragmentHomeBinding
import com.freemovies.firetv.ui.details.DetailFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class HomeFragment :
    DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home), CarouselContract {
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeAdapter(this) }

    override fun onBindView(binding: FragmentHomeBinding) {
        binding.viewModel = viewModel
        binding.rv.adapter = adapter
        viewModel.carousels.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onItemClick(rowPosition: Int, itemPosition: Int, item: BaseContentItem) {
        if (item is Movie) {
            findNavController().navigate(DetailFragmentDirections.actionToDetailFragment(item.id))
        }
    }

    override fun handleKeyEvent(
        recycler: RecyclerView?,
        view: View,
        keyCode: Int,
        keyEvent: KeyEvent,
        itemPosition: Int,
        rowPosition: Int
    ): Boolean = false

    override fun onItemFocusChanged(
        view: View,
        hasFocus: Boolean,
        itemPosition: Int,
        rowPosition: Int,
        item: BaseContentItem
    ) {
    }
}