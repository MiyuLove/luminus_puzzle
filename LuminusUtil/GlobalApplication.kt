package com.exercise.luminus_project.LuminusUtil

import android.app.Activity
import android.app.Application
import com.exercise.luminus_project.MusicManager
import com.exercise.luminus_project.NavGame.GameLogic
import kotlinx.coroutines.flow.MutableStateFlow

class GlobalApplication : Application(){
    companion object{
        lateinit var gameLogic : GameLogic
        var Global_bgmState = true
        lateinit var Global_bgmPlayer : MusicManager
        var firstScreen = true
    }
}