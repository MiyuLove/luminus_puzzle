package com.exercise.luminus_project.NavGame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.exercise.luminus_project.ComposeBox.creditText
import com.exercise.luminus_project.ComposeBox.infoText

@Composable
fun RewardScreen(modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
        infoText("재능의 아름다움은 아름다워", modifier = Modifier.fillMaxWidth(), overflow = TextOverflow.Visible)
        infoText("노력의 가치는 가치있어")
        creditText("✔ Music provided by 셀바이뮤직\n" +
                "\uD83C\uDFB5 Title : 미래도시라솔파 by 배달의민족\n" +
                "sellbuymusic.com/md/mgcwcft-gckztkn\n" +
                "✔ SFX provided by 셀바이뮤직\n"+"sellbuymusic.com/md/sapgcbw-yckztkn", fontSize = 15)


        infoText("설치해주셔서 감사합니다.")
        infoText("Developer 참구려")
    }
}