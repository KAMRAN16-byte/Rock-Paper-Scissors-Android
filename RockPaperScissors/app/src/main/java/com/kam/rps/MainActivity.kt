package com.kam.rps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kam.rps.ui.theme.RPSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RPSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RPS()
                }
            }
        }
    }
}

@Composable
fun RPS() {
    val enabler = remember {
        mutableStateOf(true)
    }
    val playerplay = remember {
        mutableStateOf("")
    }
    val computerplay = remember {
        mutableStateOf("")
    }
    val reset_enabler = remember {
        mutableStateOf(!enabler.value)
    }
    val result = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Button(
                    onClick = {
                        playerplay.value = "Rock"
                        computerplay.value = computer_play()
                        result.value = winner(playerplay,computerplay)
                        enabler.value = !enabler.value
                        reset_enabler.value = !reset_enabler.value


                    },
                    enabled = enabler.value
                ) {
                    Text(text = "Rock")
                }
                Spacer(modifier = Modifier.width(50.dp))
                Button(onClick = {
                    playerplay.value = "Paper"
                    computerplay.value = computer_play()
                    result.value = winner(playerplay,computerplay)
                    enabler.value = !enabler.value
                    reset_enabler.value = !reset_enabler.value
                },
                    enabled = enabler.value) {
                    Text(text = "Paper")
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Box {
                Button(onClick = {
                    playerplay.value = "Scissor"
                    computerplay.value = computer_play()
                    result.value = winner(playerplay,computerplay)
                    enabler.value = !enabler.value
                    reset_enabler.value = !reset_enabler.value
                    },
                    enabled = enabler.value) {
                    Text(text = "Scissor")
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(modifier = Modifier.width(215.dp),horizontalAlignment = Alignment.Start) {
            Text(text = "You Played: ${playerplay.value}")
            Text(text = "Computer Played: ${computerplay.value}")
            Text(text = "Result: ${result.value}")
        }
        Spacer(modifier = Modifier.height(50.dp))
         Column(modifier = Modifier.width(215.dp),horizontalAlignment = Alignment.CenterHorizontally) {
             Button(onClick = {
                 playerplay.value = ""
                 computerplay.value = ""
                 result.value = ""
                 enabler.value = !enabler.value
                 reset_enabler.value = !reset_enabler.value
             },
                 enabled = reset_enabler.value) {
                Text(text = "Reset")
             }
         }

    }
}
fun computer_play() : String{
    return when((1..3).random()){
        1 -> "Rock"
        2-> "Paper"
        else -> "Scissor"
    }
}
fun winner(Player_Play: MutableState<String>,Computer_Play: MutableState<String>):String{
         return when{
            Player_Play.value == Computer_Play.value -> "Tie!"
            Player_Play.value == "Rock" && Computer_Play.value == "Scissor" -> "Player Wins!"
            Player_Play.value == "Paper" && Computer_Play.value == "Rock" -> "Player Wins!"
            Player_Play.value == "Scissor" && Computer_Play.value == "Paper" -> "Player Wins!"
            else -> "Computer Wins!"
        }
}

@Preview(showBackground = true)
@Composable
fun RPSPreview(){
    RPS()
}