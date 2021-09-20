package com.lombaa.firetv.base.dialog

import androidx.lifecycle.LiveData
import com.lombaa.firetv.base.asMutable
import com.lombaa.firetv.base.lifecycle.SingleLiveData

interface DialogViewModelDelegate {

    val showDialogEvent: LiveData<DialogConfig>

    val dismissDialogEvent: LiveData<Unit>

    fun showDialog(config: DialogConfig)

    fun dismissDialog()
}

internal class DialogViewModelDelegateImpl : DialogViewModelDelegate {

    override val showDialogEvent: LiveData<DialogConfig> = SingleLiveData()

    override val dismissDialogEvent: LiveData<Unit> = SingleLiveData()

    override fun showDialog(config: DialogConfig) {
        showDialogEvent.asMutable().value = config
    }

    override fun dismissDialog() {
        dismissDialogEvent.asMutable().value = Unit
    }
}