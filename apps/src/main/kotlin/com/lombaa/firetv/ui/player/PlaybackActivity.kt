package com.lombaa.firetv.ui.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import com.lombaa.firetv.ui.player.VideoFragment.Companion.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaybackActivity : FragmentActivity() {

    companion object {
        fun createIntent(context: Context, movieId: String): Intent {
            return Intent(context, PlaybackActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, VideoFragment()).commit()
        }
    }
}