package com.freemovies.firetv.ui.player

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ext.leanback.LeanbackPlayerAdapter
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoFragment : VideoSupportFragment() {

    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<LeanbackPlayerAdapter>
    private var player: SimpleExoPlayer? = null
    private val viewModel: VideoViewModel by viewModels()

    companion object {
        const val MOVIE_ID = "movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.apply {
            val trackSelector = DefaultTrackSelector(this)
            trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd())
            player = SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).build().also {
                val glueHost = VideoSupportFragmentGlueHost(this@VideoFragment)
                val playerAdapter = LeanbackPlayerAdapter(this, it, 100);
                mTransportControlGlue = PlaybackTransportControlGlue(activity, playerAdapter)
                mTransportControlGlue.host = glueHost
                mTransportControlGlue.isSeekEnabled = true
                mTransportControlGlue.playWhenPrepared()
                playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.apply {
            loadMovieDetail(intent.getStringExtra(MOVIE_ID) ?: "")
        }
    }

    override fun onPause() {
        super.onPause()
        mTransportControlGlue.pause()
    }

    override fun onResume() {
        super.onResume()
        hideControlsOverlay(true)

        if (!mTransportControlGlue.isPlaying) {
            mTransportControlGlue.play()
        }
    }

    override fun onStop() {
        player?.release()
        super.onStop()
    }

    private fun loadMovieDetail(movieId: String) {
        viewModel.currentMovie.observe(viewLifecycleOwner) { movie ->
            mTransportControlGlue.title = movie.title
            mTransportControlGlue.subtitle = movie.desc
            val uri = Uri.parse(movie.videoUrl)
            val mediaItem = MediaItem.fromUri(uri)
            player?.apply {
                setMediaItem(mediaItem)
                playWhenReady = true
            }
            addPlayerListeners()
        }

        viewModel.loadDetails(movieId)
    }

    private fun addPlayerListeners() {
        player?.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                player?.release()
                activity?.finish()
            }

            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    player?.release()
                    activity?.finish()
                }
            }
        })
    }
}