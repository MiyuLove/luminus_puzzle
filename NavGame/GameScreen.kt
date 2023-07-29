package com.exercise.luminus_project.NavGame

import android.app.Activity
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exercise.luminus_project.ComposeBox.SquareListClickedBtn
import com.exercise.luminus_project.ComposeBox.mainTitle
import com.exercise.luminus_project.ComposeBox.puzzleButton
import com.exercise.luminus_project.LuminusUtil.GlobalApplication.Companion.gameLogic
import com.exercise.luminus_project.R
import com.exercise.luminus_project.ui.theme.BgGray
import com.exercise.luminus_project.ui.theme.BtnBlue
import com.exercise.luminus_project.ui.theme.PuzzleBeige
import com.exercise.luminus_project.ui.theme.PuzzleBlue
import com.exercise.luminus_project.ui.theme.PuzzleGreen
import com.exercise.luminus_project.ui.theme.PuzzleRed
import kotlinx.coroutines.flow.MutableStateFlow

private val soundPool = SoundPool.Builder().build()
private var _soundState = MutableStateFlow(true)
private lateinit var iconImage : Array<Painter>

@Composable
fun GamePlayScreen(nc : NavController){
    iconImage = arrayOf(painterResource(id = R.drawable.volume_logo_on), painterResource(id = R.drawable.volume_logo_off))
    var iconIdx by remember { mutableStateOf(0) }

    val oc = {
        gameLogic.resetBtn()
    }
    val fc = {
        nc.popBackStack()
        nc.navigate(GameForNav.GameMenu.route)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BgGray)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(0.15f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.15f))
            mainTitle("Luminus Puzzle", modifier = Modifier.weight(0.7f))
            Image(
                painter = iconImage[iconIdx],
                contentDescription = "",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        _soundState.value = !_soundState.value
                        iconIdx = if (iconIdx == 0) 1 else 0
                    }
                    .weight(0.15f)
            )

        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(0.75f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            gameBoard(fc)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            SquareListClickedBtn(oc,"reset",1234, modifier = Modifier.fillMaxWidth(), BtnBlue)
        }
    }
}

@Composable
fun gameBoard(fc : () -> Unit){
    val col = gameLogic.col
    val row = gameLogic.row

    val colWeight = 1f/col
    val rowWeight = 1f/row

    val colorArr = arrayOf(PuzzleGreen, PuzzleBlue, PuzzleBeige, PuzzleRed, Color.Yellow,Color.Black)

    val q = LocalContext.current

    var soundId = soundPool.load(q,R.raw.ws,1)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)) {
        var btnIdx = 0
        for (i in 0 until col) {
            Row(modifier = Modifier.weight(colWeight)) {
                for (j in 0 until row) {
                    puzzleButton(
                        onClick = {
                            if(_soundState.value)
                                soundPool.play(soundId,1.0f,1.0f,0,0,1.0f)
                            if(gameLogic.setData(i,j)) {
                                fc()
                            }
                        },
                        text = "",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(rowWeight),
                        buttonColor = colorArr[gameLogic.States[btnIdx].value],
                    )
                    btnIdx ++
                }
            }
        }
    }
}