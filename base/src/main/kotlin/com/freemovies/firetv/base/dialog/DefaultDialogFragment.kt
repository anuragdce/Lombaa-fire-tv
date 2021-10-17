package com.freemovies.firetv.base.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onShow
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.list.listItems
import com.freemovies.firetv.base.R
import com.freemovies.firetv.base.anyNotNull
import kotlin.reflect.KClass

class DefaultDialogFragment private constructor() : DialogFragment() {

    private val viewModel: DialogViewModelDelegate by dialogViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val config = viewModel.showDialogEvent.value ?: throw IllegalStateException("Trying to create a dialog with null config")
        return when (config) {
            is DialogConfig.Alert -> createAlertDialog(config)
            is DialogConfig.BottomSheet -> createBottomSheetDialog(config)
            is DialogConfig.Progress -> createProgressDialog()
            is DialogConfig.Input -> createInputDialog(config)
        }
    }

    private fun createAlertDialog(config: DialogConfig.Alert): Dialog =
        MaterialDialog(requireContext()).show {
            if (anyNotNull(config.titleStringRes, config.title)) {
                title(res = config.titleStringRes, text = config.title)
            }
            if (anyNotNull(config.messageStringRes, config.message)) {
                message(res = config.messageStringRes, text = config.message)
            }
            if (anyNotNull(config.positiveStringRes, config.positiveText, config.positiveCallback)) {
                positiveButton(res = config.positiveStringRes, text = config.positiveText) {
                    config.positiveCallback?.invoke()
                }
            }

            if (anyNotNull(config.negativeStringRes, config.negativeText, config.negativeCallback)) {
                negativeButton(res = config.negativeStringRes, text = config.negativeText) {
                    config.negativeCallback?.invoke()
                }
            }
        }

    private fun createBottomSheetDialog(config: DialogConfig.BottomSheet): Dialog =
        MaterialDialog(requireContext(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            if (anyNotNull(config.titleStringRes, config.title)) {
                title(res = config.titleStringRes, text = config.title)
            }
            if (anyNotNull(config.messageStringRes, config.message)) {
                message(res = config.messageStringRes, text = config.message)
            }
            if (anyNotNull(config.itemsArrayRes, config.items, config.selectionCallback)) {
                listItems(res = config.itemsArrayRes, items = config.items) { _, index, _ ->
                    config.selectionCallback?.invoke(index)
                }
            }
        }

    private fun createProgressDialog(): Dialog =
        MaterialDialog(requireContext()).show {
            customView(viewRes = R.layout.view_progress)
            cancelable(false)
            cancelOnTouchOutside(false)
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
            onShow {
                window?.apply {
                    setDimAmount(0f)
                    setLayout(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
            }
        }

    private fun createInputDialog(config: DialogConfig.Input): Dialog =
        MaterialDialog(requireContext()).show {
            if (anyNotNull(config.titleStringRes, config.title)) {
                title(res = config.titleStringRes, text = config.title)
            }
            input(prefill = config.prefillText) { dialog, text ->
                config.inputCallback?.invoke(text.toString())
            }

            if (anyNotNull(config.positiveStringRes, config.positiveText)) {
                positiveButton(res = config.positiveStringRes, text = config.positiveText)
            }

            if (anyNotNull(config.negativeStringRes, config.negativeText)) {
                negativeButton(res = config.negativeStringRes, text = config.negativeText)
            }
        }

    @Suppress("UNCHECKED_CAST")
    private fun dialogViewModel(): Lazy<DialogViewModelDelegate> = lazy {
        val arguments = requireNotNull(arguments)
        val viewModelClass = (requireNotNull(arguments.getSerializable(VIEW_MODEL_CLASS_PARAM)) as Class<out ViewModel>).kotlin
        val lazyViewModel = createViewModelLazy(
            viewModelClass,
            { requireActivity().viewModelStore },
            { requireActivity().defaultViewModelProviderFactory }
        )
        lazyViewModel.value as DialogViewModelDelegate
    }

    companion object {

        private const val DISMISS_DIALOG_TAG = "dismiss_default_dialog_fragment"
        private const val STACK_DIALOG_TAG = "stack_default_dialog_fragment"
        private const val VIEW_MODEL_CLASS_PARAM = "view_model_class"

        fun <T> show(viewModelClass: KClass<T>, parent: Fragment, stack: Boolean = false) where T : ViewModel, T : DialogViewModelDelegate {
            if (!stack) {
                dismiss(parent)
            }
            val bundle = Bundle().apply {
                putSerializable(VIEW_MODEL_CLASS_PARAM, viewModelClass.java)
            }
            val dialogFragment = DefaultDialogFragment().apply {
                arguments = bundle
            }
            val tag = if (stack) STACK_DIALOG_TAG else DISMISS_DIALOG_TAG
            dialogFragment.show(parent.childFragmentManager, tag)
        }

        fun dismiss(parent: Fragment) {
            val existingFragment = parent.childFragmentManager.findFragmentByTag(DISMISS_DIALOG_TAG)
            if (existingFragment is DialogFragment) {
                existingFragment.dismiss()
            }
        }
    }
}