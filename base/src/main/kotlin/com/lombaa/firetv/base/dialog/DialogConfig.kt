package com.lombaa.firetv.base.dialog

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import com.lombaa.firetv.base.R

sealed class DialogConfig {

    abstract val stack: Boolean

    data class Alert(
        override val stack: Boolean = false,
        val title: String? = null,
        @StringRes val titleStringRes: Int? = null,
        val message: String? = null,
        @StringRes val messageStringRes: Int? = null,
        val positiveText: String? = null,
        @StringRes val positiveStringRes: Int? = R.string.ok,
        val positiveCallback: (() -> Unit)? = null,
        val negativeText: String? = null,
        @StringRes val negativeStringRes: Int? = null,
        val negativeCallback: (() -> Unit)? = null
    ) : DialogConfig()

    data class BottomSheet(
        override val stack: Boolean = false,
        val title: String? = null,
        @StringRes val titleStringRes: Int? = null,
        val message: String? = null,
        @StringRes val messageStringRes: Int? = null,
        val items: List<CharSequence>? = null,
        @ArrayRes val itemsArrayRes: Int? = null,
        val selectionCallback: ((index: Int) -> Unit)? = null
    ) : DialogConfig()

    data class Input(
        override val stack: Boolean = false,
        val title: String? = null,
        val prefillText: String? = null,
        @StringRes val titleStringRes: Int? = null,
        val positiveText: String? = null,
        @StringRes val positiveStringRes: Int? = null,
        val inputCallback: ((content: String) -> Unit)? = null,
        val negativeText: String? = null,
        @StringRes val negativeStringRes: Int? = null
    ) : DialogConfig()

    data class Progress(
        override val stack: Boolean = false,
    ) : DialogConfig()
}