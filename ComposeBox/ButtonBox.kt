package com.exercise.luminus_project.ComposeBox

import android.annotation.SuppressLint
import android.app.Activity
import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exercise.luminus_project.R
import com.exercise.luminus_project.ViewModelBox.SelectViewModel
import com.exercise.luminus_project.ui.theme.BtnMint
import com.exercise.luminus_project.ui.theme.BtnNavy
import com.exercise.luminus_project.ui.theme.BtnPurple
import com.exercise.luminus_project.ui.theme.LineGreen


@Composable
fun btnText(text : String, fontSize: Int){
    Text(
        text = text,
        fontSize = fontSize.sp,
        maxLines = 2,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.base_font)),
        modifier = Modifier.padding(5.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun signForBtn(
    text: String,
    oc : ()-> Unit,
    modifier: Modifier,
    fontSize : Int,
){
    Button(
        onClick = oc,
        colors = ButtonDefaults.buttonColors(
            containerColor = BtnPurple
        )
        , modifier = modifier,
        contentPadding = PaddingValues(start =  0.dp, end = 0.dp),

        ){
        btnText(text,fontSize)
    }
}

@Composable
fun SquareListClickedBtn(
    onClick: () -> Unit,
    text : String,
    num : Int,
    modifier: Modifier,
    buttonColor : Color
){
    Button(
        onClick = onClick,
        shape = RectangleShape,
        modifier = modifier.border(2.dp, LineGreen),
        contentPadding = PaddingValues(start =  0.dp, end = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        )
    ) {
        btnText(text,25)
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SquareBtnList(
    num : Int = 0, modifier: Modifier, model: SelectViewModel, oc : () -> Unit = {}
){
    var size = model.size
    var stageFirst : Array<String>
    var btnColor1 : Color
    var btnColor2 : Color

    if(num == 0){
        stageFirst = arrayOf("2x2","2x3","3x3","3x4","4x4","4x5","5x5","5x6",)
        btnColor1 = BtnNavy
        btnColor2 = Color.Yellow
    }
    else if(num == 1){
        stageFirst = arrayOf("2 color","3 color","4 color","5 color","6 color","7 color")
        btnColor1 = BtnPurple
        btnColor2 = Color.Yellow
    }else{
        stageFirst = arrayOf("2 range","3 range","4 range")
        btnColor1 = BtnMint
        btnColor2 = Color.Yellow
    }

    AnimatedVisibility(visible = model.columnState.collectAsState().value || num == 0) {
        Column(modifier = modifier){
            for (i in 0 until size){
                SquareListClickedBtn(
                    onClick = {
                        model.updateBtnStates(i,i)
                        oc()
                    },
                    text = stageFirst[i],
                    num = i,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.12f),
                    buttonColor = if(model.btnStates[i].value == -1) btnColor1 else btnColor2
                )
            }
        }
    }
}

@Composable
fun puzzleButton(
    onClick: () -> Unit,
    text : String,
    modifier: Modifier,
    buttonColor : Color,
){
    var ButtonColor = buttonColor

    Button(
        onClick = onClick,
        shape = RectangleShape,
        modifier = modifier.border(2.dp, LineGreen),
        contentPadding = PaddingValues(start =  0.dp, end = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor
        )
    ) {
        btnText(text,25)
    }
}