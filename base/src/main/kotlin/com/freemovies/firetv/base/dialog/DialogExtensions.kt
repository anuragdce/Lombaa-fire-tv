package com.freemovies.firetv.base.dialog

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

fun <T> Fragment.observeDialogs(viewModel: T) where T : ViewModel, T : DialogViewModelDelegate {
    viewModel.showDialogEvent.observe(viewLifecycleOwner) { config ->
        DefaultDialogFragment.show(viewModel::class, this, config.stack)
    }
    viewModel.dismissDialogEvent.observe(viewLifecycleOwner) {
        DefaultDialogFragment.dismiss(this)
    }
}
