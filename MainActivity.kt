package com.exercise.luminus_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.rememberNavController
import com.exercise.luminus_project.LuminusUtil.GlobalApplication.Companion.Global_bgmPlayer
import com.exercise.luminus_project.LuminusUtil.GlobalApplication.Companion.Global_bgmState
import com.exercise.luminus_project.LuminusUtil.GlobalApplication.Companion.firstScreen
import com.exercise.luminus_project.NavGame.NavGraphGame
import com.exercise.luminus_project.ui.theme.Luminus_projectTheme

private lateinit var musicManager: MusicManager


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        musicManager = MusicManager(this)
        Global_bgmPlayer = musicManager
        musicManager.ServiceStart()

        super.onCreate(savedInstanceState)

        setContent {
            Luminus_projectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameNav()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if(Global_bgmState) musicManager.ServiceStop()
    }

    override fun onResume() {
        if(Global_bgmState)musicManager.ServiceStart()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()

        ActivityCompat.finishAffinity(this)
        System.exit(0)
    }

}
@Composable
fun GameNav(){
    val navController = rememberNavController()
    NavGraphGame(navController)
}