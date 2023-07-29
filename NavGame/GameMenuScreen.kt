package com.exercise.luminus_project.NavGame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exercise.luminus_project.ComposeBox.infoText
import com.exercise.luminus_project.ui.theme.BgGray

@Composable
fun GameRewardScreen(nc : NavController){
    Column(
        Modifier
            .fillMaxWidth()
            .background(BgGray),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Column(modifier = Modifier.weight(0.3f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            infoText("이 어려운 걸 깨셨다니!\n 당신은 천재?\n아직 보상은 없어요\n 응원말이라도 준비했어요")
        }
        RewardScreen(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .weight(0.7f)
        )
    }
}