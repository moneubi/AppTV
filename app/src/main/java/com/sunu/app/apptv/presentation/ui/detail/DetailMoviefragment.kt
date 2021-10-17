package com.sunu.app.apptv.presentation.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util
import com.sunu.app.apptv.R
import com.sunu.app.apptv.common.Constatnts
import com.sunu.app.apptv.domain.model.ContentList
import com.sunu.app.apptv.domain.model.Movies
import kotlinx.android.synthetic.main.detail_movie_fragment.*

class DetailMoviefragment : Fragment() {

    companion object {
        fun newInstance() = DetailMoviefragment()
    }

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private lateinit var movie: Movies
    /*===========================================================*/
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    private val TAG = "PlayerActivity"
    private val playbackStateListener: Player.EventListener = playbackStateListener()
    /*===========================================================*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        getSaisons()

        Glide.with(requireContext())
            .load("${Constatnts.BASE_URL}${movie.fullscreenimageurl}")
            .placeholder(R.drawable.placeholder)
            .into(img_fulcreen)
        val title = movie.title[0]
        txt_title_movie.text = title.value
        txt_subtitle.text = movie.subtitle
        /*====================================================*/
        img_play.setOnClickListener {
            img_fulcreen.visibility = View.GONE
            img_play.visibility = View.GONE
            video_view.visibility = View.VISIBLE
            progress_waiting.visibility = View.VISIBLE
            if (Util.SDK_INT >= 24) {
                initializePlayer()
            }
        }
    }

    fun getSaisons(){
        detailMovieViewModel.getContents(movie.id)
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initViewModel(){
        detailMovieViewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        // TODO: Use the ViewModel
        detailMovieViewModel.setMovieShared()
        movie = detailMovieViewModel.getMovieShared()

        detailMovieViewModel.getContentListobservable().observe(this, Observer<ContentList?> {
            if (it == null){
                Toast.makeText(context,"Content null", Toast.LENGTH_LONG).show()
            }else{
                if (!it.contents.pitch.isNullOrEmpty()) {
                    txt_pitch.text = it.contents.pitch
                }
            }
        })
    }

    /**
     * initialize player
     * */
    fun initializePlayer() {
        player = SimpleExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                video_view.player = exoPlayer
                val mediaItem = MediaItem.fromUri("https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-\n" +
                        "bf1d-4e3d-8899-f0f6155f6efa.mpd")
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.addListener(playbackStateListener)
                //exoPlayer.play()
                exoPlayer.prepare()
            }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }


    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        video_view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            removeListener(playbackStateListener)
            release()
        }
        player = null
    }

    private fun playbackStateListener() = object : Player.EventListener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED"
                else -> "UNKNOWN_STATE"
            }
            Log.d(TAG, "changed state to $stateString")
            if (!stateString.isNullOrEmpty()){
                progress_waiting.visibility = View.GONE
            }
        }
    }
}