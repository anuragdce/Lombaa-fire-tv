package com.lombaa.firetv.base.databinding

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("selected")
fun setSelected(view: View, selected: Boolean) {
  view.isSelected = selected
}

@BindingAdapter("enabled")
fun setEnabled(view: View, enabled: Boolean) {
  view.isEnabled = enabled
}

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
  view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("invisible")
fun setInvisible(view: View, invisible: Boolean) {
  view.visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("bold")
fun setBold(textView: TextView, bold: Boolean) {
  if (bold) {
    textView.typeface = Typeface.DEFAULT_BOLD
  } else {
    textView.typeface = Typeface.DEFAULT
  }
}

@BindingAdapter("textResId")
fun setTextResId(textView: TextView, resId: Int) {
  val text = if (resId > 0) textView.context.getText(resId) else null
  textView.text = text
}

