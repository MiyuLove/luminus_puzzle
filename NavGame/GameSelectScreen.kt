package com.exercise.luminus_project.NavGame

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exercise.luminus_project.ComposeBox.SelectDialog
import com.exercise.luminus_project.ComposeBox.SquareBtnList
import com.exercise.luminus_project.ComposeBox.SquareListClickedBtn
import com.exercise.luminus_project.ComposeBox.mainTitle
import com.exercise.luminus_project.LuminusUtil.GlobalApplication.Companion.gameLogic
import com.exercise.luminus_project.LuminusUtil.projectUtil
import com.exercise.luminus_project.ViewModelBox.SelectViewModel
import com.exercise.luminus_project.ui.theme.BgGray
import com.exercise.luminus_project.ui.theme.BtnBlue

@Composable
fun SelectScreen(nc : NavController){
    val activity = LocalContext.current as Activity
    var selectModel = arrayOf(SelectViewModel(8), SelectViewModel(4), SelectViewModel(3))

    var stageInfoFromUser = arrayOf(
        arrayOf("2x2","2x3","3x3","3x4","4x4","4x5","5x5","5x6",),
        arrayOf("2c","3c","4c","5c","6c","7c"),
        arrayOf("2r","3r","4r","5r","6r")
    )
    val stageInfoText = remember { mutableStateOf("asdf") }
    val showDialog = remember { mutableStateOf(false) }

    if(showDialog.value)
        SelectDialog("Stage Info",setShowDialog = {
            showDialog.value = it
        },
            {
                nc.navigate(GameForNav.GameScreen.route)
            },
            stageInfoText.value
        )
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BgGray)) {
        Column(
            modifier = Modifier
                .weight(0.15f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            mainTitle("Select Stage")
        }

        selectModel[0].updateListStates(true)

        Row(modifier = Modifier
            .weight(0.85f)
            .fillMaxWidth()
            .padding(10.dp)) {
            Box(modifier = Modifier.weight(0.33f)){
                SquareBtnList(
                    num = 0,modifier = Modifier,
                    model = selectModel[0],
                    oc = {
                        selectModel[1].updateListStates(true)
                        selectModel[2].updateListStates(false)

                        selectModel[1].initiateBtnStates()
                        selectModel[2].initiateBtnStates()
                    }
                )
            }
            Box(modifier = Modifier.weight(0.33f)){
                SquareBtnList(
                    num = 1,modifier = Modifier,
                    model = selectModel[1],
                    oc = {
                        selectModel[2].updateListStates(true)
                    }
                )
            }
            Box(modifier = Modifier.weight(0.33f)){
                SquareBtnList(
                    num = 2,modifier = Modifier,
                    model = selectModel[2],
                    oc = {}
                )
            }
        }
        SquareListClickedBtn(
            onClick = {
                var clickedValue = arrayOf(
                    selectModel[0].sTState.value,
                    selectModel[1].sTState.value,
                    selectModel[2].sTState.value
                )
                var clickedDialogButton = true
                for(i in clickedValue)if(i == -1)clickedDialogButton = false

                if(clickedDialogButton){
                    gameLogic = GameLogic(clickedValue[0],clickedValue[1],clickedValue[2])

                    stageInfoText.value = stageInfoFromUser[0][clickedValue[0]] +
                            "-" + stageInfoFromUser[1][clickedValue[1]] +
                            "-" + stageInfoFromUser[2][clickedValue[2]]
                    showDialog.value = true
                }
                else{
                    projectUtil.makeToast(activity,"한 줄에 한 칸 선택")
                }
            },
            text = "play",
            1234,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            BtnBlue
        )
    }
}
