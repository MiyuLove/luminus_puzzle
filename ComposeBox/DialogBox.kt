package com.exercise.luminus_project.ComposeBox

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.exercise.luminus_project.R
import com.exercise.luminus_project.ui.theme.BgGray
import com.exercise.luminus_project.ui.theme.BgGreen

@Composable
fun GameDialog(value: String, setShowDialog: (Boolean) -> Unit, topBtn : () -> Unit, btmBtn : () -> Unit) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                      //  textFieldCaptionText(caption = "프로필 사진")
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Column(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = topBtn,
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "카 메 라", fontSize = 30.sp, fontFamily = FontFamily(Font(R.font.base_font)))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = btmBtn,
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "갤 러 리", fontSize = 30.sp, fontFamily = FontFamily(Font(R.font.base_font)))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SelectDialog(text: String, setShowDialog: (Boolean) -> Unit, topBtn : () -> Unit, btmText : String) {
    val q = LocalContext.current as Activity

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = BgGreen
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    mainTitle(text, fontSize = 30)
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        tint = colorResource(android.R.color.darker_gray),
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clickable { setShowDialog(false) }
                    )
                }

                infoText(btmText, modifier = Modifier.fillMaxWidth().padding(10.dp))

                signForBtn(
                    text = "Start",
                    oc = topBtn,
                    modifier = Modifier.fillMaxWidth(), fontSize = 30
                )
            }

        }
    }
}