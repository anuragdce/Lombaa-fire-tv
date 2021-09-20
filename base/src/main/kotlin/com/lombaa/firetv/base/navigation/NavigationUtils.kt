package com.lombaa.firetv.base.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fondesa.kpermissions.coroutines.sendSuspend
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.isDenied

inline fun <reified T : Activity> Activity.startActivity() {
  val intent = Intent(this, T::class.java)
  startActivity(intent)
}

inline fun <reified T : Activity> Fragment.startActivity() {
  val intent = Intent(requireContext(), T::class.java)
  startActivity(intent)
}

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int) {
  val intent = Intent(this, T::class.java)
  startActivityForResult(intent, requestCode)
}

fun Fragment.createActivityResultLauncher(callback: ActivityResultCallback<ActivityResult>) =
  registerForActivityResult(ActivityResultContracts.StartActivityForResult(), callback)

inline fun <reified T : Activity> Fragment.launchActivityForResult(
  launcher: ActivityResultLauncher<Intent>,
  data: Bundle? = null
) {
  val intent = Intent(requireContext(), T::class.java)
  data?.let {
    intent.putExtras(it)
  }
  launcher.launch(intent)
}

fun Activity.setResultAndFinish(resultCode: Int, data: Intent? = null) {
  setResult(resultCode, data)
  finish()
}

fun Fragment.setResultAndFinish(resultCode: Int, data: Intent? = null) {
  requireActivity().setResultAndFinish(resultCode, data)
}

inline fun <reified T : Parcelable> Bundle.getParcelable() =
  this.getParcelable<T>(T::class.java.name)

fun Bundle.putParcelable(value: Parcelable) {
  putParcelable(value::class.java.name, value)
}

fun Intent.putExtra(value: Parcelable) {
  putExtra(value::class.java.name, value)
}

inline fun <reified T : Parcelable> Intent.getParcelableExtra() =
  this.getParcelableExtra<T>(T::class.java.name)

inline fun <reified T : Parcelable> ActivityResult.getParcelable(): T? {
  if (resultCode == Activity.RESULT_OK) {
    data?.let { intent ->
      return intent.getParcelableExtra()
    }
  }
  return null
}

fun Fragment.openBrowser(url: String) {
  val intent = Intent(Intent.ACTION_VIEW)
  intent.data = Uri.parse(url)
  startActivity(intent)
}

fun Fragment.openIntent(intent: Intent): Boolean {
  return activity?.packageManager?.let { packageManager ->
    intent.resolveActivity(packageManager)?.let {
      startActivity(intent)
      true
    } ?: false
  } ?: false

}

fun Fragment.shareImage(uri: Uri) {
  context?.let {
    val share = Intent(Intent.ACTION_SEND)
    share.type = "image/jpeg"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      val finalUri = FileProvider.getUriForFile(
        it, "${it.packageName}.provider", uri.toFile()
      )
      share.putExtra(Intent.EXTRA_STREAM, finalUri)
    } else {
      share.putExtra(Intent.EXTRA_STREAM, uri)
    }
    share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    startActivity(Intent.createChooser(share, ""))
  }
}

fun FragmentActivity.findNavHostController(@IdRes viewIdRes: Int): NavController {
  val navHostFragment = this.supportFragmentManager.findFragmentById(viewIdRes) as NavHostFragment
  return navHostFragment.navController
}

suspend fun Fragment.requestPermissions(permissions: List<String>): Boolean {
  val firstPermission = permissions.first()
  val others = permissions.subList(1, permissions.size)
  val result = permissionsBuilder(firstPermission, *others.toTypedArray()).build().sendSuspend()

  result.forEach { status ->
    if (status.isDenied()) {
      return false
    }
  }
  return true
}
