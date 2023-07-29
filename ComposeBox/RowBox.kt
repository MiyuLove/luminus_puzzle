package com.exercise.luminus_project.ComposeBox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CenterButtonRow(text : String = "", oc : () -> Unit = {},modifier: Modifier = Modifier,spacerWeight : Float = 0.2f){
    Row(Modifier.fillMaxWidth().then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.weight(spacerWeight))
        signForBtn(text = text, oc =  oc, modifier = Modifier.padding(20.dp).weight(1-spacerWeight*2), fontSize = 30)
        Spacer(modifier = Modifier.weight(spacerWeight))
    }
}