package com.exercise.luminus_project.ComposeBox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.exercise.luminus_project.R
import com.exercise.luminus_project.ui.theme.TxtSky
import com.exercise.luminus_project.ui.theme.TxtYello
import org.w3c.dom.Text

@Composable
fun mainTitle(text : String = "Luminus Puzzle",modifier: Modifier = Modifier,
              fontSize : Int = 40,textAlign: TextAlign = TextAlign.Center){
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize.sp,
        color = TxtSky,
        overflow = TextOverflow.Clip,
        fontFamily = FontFamily(Font(R.font.base_font)),
        textAlign = TextAlign.Center
    )
}

@Composable
fun infoText(text : String = "info text",modifier: Modifier = Modifier,
             fontSize : Int = 30, overflow: TextOverflow = TextOverflow.Clip){
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize.sp,
        color = TxtYello,
        overflow = overflow,
        textAlign = TextAlign.Center
    )
}

@Composable
fun creditText(text : String = "resource",modifier: Modifier = Modifier,
               fontSize : Int = 10){
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize.sp,
        color = TxtYello,
        textAlign = TextAlign.Center
    )
}
