package com.exercise.luminus_project.NavGame

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.exercise.luminus_project.ComposeBox.CenterButtonRow
import com.exercise.luminus_project.ComposeBox.mainTitle
import com.exercise.luminus_project.LuminusUtil.GlobalApplication
import com.exercise.luminus_project.LuminusUtil.GlobalApplication.Companion.Global_bgmPlayer
import com.exercise.luminus_project.LuminusUtil.GlobalApplication.Companion.Global_bgmState
import com.exercise.luminus_project.LuminusUtil.GlobalApplication.Companion.firstScreen
import com.exercise.luminus_project.ui.theme.BgGray
import com.exercise.luminus_project.ui.theme.BtnNavy
import com.exercise.luminus_project.ui.theme.PuzzleBabyPurple
import com.exercise.luminus_project.ui.theme.PuzzleBlue
import com.exercise.luminus_project.ui.theme.PuzzleGreen
import com.exercise.luminus_project.ui.theme.PuzzleOrange
import com.exercise.luminus_project.ui.theme.PuzzleWhiteYellow
import kotlin.random.Random

private val randomColorGroup = arrayOf(
    Color.Red, PuzzleOrange, PuzzleWhiteYellow, PuzzleGreen, PuzzleBlue, BtnNavy,
    PuzzleBabyPurple, Color.White)

@Composable
fun MainScreen(nc : NavController){
    firstScreen = true
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgGray),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f),
            verticalArrangement = Arrangement.Center
        ) {
            mainTitle(fontSize = 50)
        }
        RandomColorSquare(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .weight(0.35f)
            .background(randomColorGroup[Random.nextInt(0, 8)]),0
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        )
        {
            CenterButtonRow("start", oc = {
                firstScreen = false
                nc.navigate(GameForNav.SelectScreen.route)
            })
            CenterButtonRow("reward", oc ={
                firstScreen = false
                nc.navigate(GameForNav.GameMenu.route)
            })
            CenterButtonRow("Bgm ON/OFF", oc = {
                Global_bgmState = !Global_bgmState
                if(Global_bgmState) {
                    Global_bgmPlayer.ServiceStart()
                }
                else{
                    Global_bgmPlayer.ServiceStop()
                }

            })
            CenterButtonRow("exit",oc = {
                System.exit(0)
            })
        }
    }
}

@Composable
fun RandomColorSquare(modifier: Modifier, recursive_count : Int){
    //this recursive_count is number of squares made in this code.
    if(recursive_count == 9)return
    Column(modifier = modifier) {
        RandomColorSquare(modifier = Modifier
            .padding(15.dp)
            .background(randomColorGroup[Random.nextInt(0, 8)])
            .fillMaxSize(), recursive_count + 1)
    }
}