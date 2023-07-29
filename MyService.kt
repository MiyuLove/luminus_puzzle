package com.exercise.luminus_project

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.exercise.luminus_project.LuminusUtil.projectUtil

lateinit var mp : MediaPlayer

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this,R.raw.bgmss)
        mp.isLooping = true
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
        mp.release()
    }
}

class MusicManager(activity : Activity){
    private val activityForMusicManager : Activity
    init{
        activityForMusicManager = activity
    }

    fun soundVolumeSet(leftVolume : Float, rightVolume: Float = leftVolume){
        try {
            mp.setVolume(leftVolume, rightVolume)
        }catch (e : Exception){ }
    }

    fun ServiceStop(){
        val intent = Intent(activityForMusicManager,MyService::class.java)
        activityForMusicManager.stopService(intent)
    }

    fun ServiceStart(){
        val intent = Intent(activityForMusicManager,MyService::class.java)
        activityForMusicManager.startService(intent)
    }

}