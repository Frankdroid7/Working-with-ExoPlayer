package com.example.franklin.workingwithexoplayer

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var exoPlayer: SimpleExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var playMediaButton: Button
    private lateinit var videoSource1: MediaSource
    private lateinit var videoSource2: MediaSource
    private lateinit var mediaSource: ConcatenatingMediaSource
    private lateinit var dataSourceFactory: DataSource.Factory
    private val videoURL =
            "https://vodcmssec-a.akamaihd.net/omnisport/ready/HD/transferscript/287/240219-EN-PERFORM-KLOPP-SALAH-RV-2_1551036339725_287.mp4"
    private val videoURL2 =
            "https://vodcmssec-a.akamaihd.net/omnisport/ready/HD/ptv_omni_ready/287/020319-EN-PERFORM-ROONEY-SURPRISESFAN-VL-2_1551533220348_287.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("AppState", "App has entered the Created State")

        playerView = findViewById(R.id.playerView)
        playMediaButton = findViewById(R.id.playMedia_button)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(this)

        dataSourceFactory = DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)))

        videoSource1 = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(videoURL))
        videoSource2 = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(videoURL2))

        mediaSource = ConcatenatingMediaSource(videoSource1,videoSource2)

        playMediaButton.setOnClickListener {
            playMedia()
        }

    }

    private fun playMedia() {

        Log.i("playMedia", "PlayMediaFunctionTriggered")

        playerView.player = exoPlayer
        exoPlayer.prepare(mediaSource)
    }

    override fun onResume() {
        super.onResume()
        Log.i("AppState", "App has entered the Resumed State")

    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}


