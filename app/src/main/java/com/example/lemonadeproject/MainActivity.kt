package com.example.lemonadeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeproject.ui.theme.LemonadeProjectTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier){

    var currentStep by remember { mutableIntStateOf(1)}
    var squeezeStep by remember { mutableIntStateOf(0)}
    Box (modifier) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(16.dp)
        ) {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        Column {
            when (currentStep) {
                1 -> LemonTextAndImage(
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionId = R.string.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeStep = (2..4).random()
                    })

                2 -> LemonTextAndImage(
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionId = R.string.squezee_lemon,
                    onImageClick = {
                        squeezeStep--
                        if (squeezeStep == 0) {
                            currentStep = 3
                        }
                    })

                3 -> LemonTextAndImage(
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionId = R.string.lemonade,
                    onImageClick = {
                        currentStep = 4
                    })

                4 -> LemonTextAndImage(
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionId = R.string.empty_glass,
                    onImageClick = { currentStep = 1 })
            }
        }
    }
}

@Composable
fun LemonTextAndImage(drawableResourceId : Int, contentDescriptionId : Int, onImageClick : () -> Unit, modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                colors = ButtonDefaults.buttonColors(Color(0xFF97dbc2)),
                modifier = Modifier
                    .drawBehind {
                        drawCircle(color = Color(0xFF97dbc2), radius = 350f)
                    }
            ) {
                Image(painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionId)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(contentDescriptionId),
                fontSize = 20.sp,
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DefaultPreview(){
    LemonadeProjectTheme {
        LemonadeApp()
    }
}