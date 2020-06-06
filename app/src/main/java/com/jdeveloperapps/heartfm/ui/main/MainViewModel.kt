package com.jdeveloperapps.heartfm.ui.main

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), MediaPlayer.OnPreparedListener {

    val DATA_STREAM = "http://gtrk22.ru:8000/HFMhigh"

    val mediaPlayer = MediaPlayer()
    val liveData = MutableLiveData<Boolean>().apply { value = false }

    init {
        mediaPlayer.setDataSource(DATA_STREAM)
        mediaPlayer.setOnPreparedListener(this)
    }

    fun getData(): LiveData<Boolean> = liveData

    fun play() {
        mediaPlayer.prepareAsync()
    }

    fun stop() {
        mediaPlayer.stop()
        liveData.value = false
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer.start()
        liveData.value = true
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer.release()
    }
}
