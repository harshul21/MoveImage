package com.example.moveimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moveimage.ui.theme.MoveImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {

    var animatedProgressX = remember { Animatable(1.5f) }

    var animatedProgressY = remember { Animatable(1.5f) }

    var animatedTranslateY = remember {
        Animatable(0f)
    }

    var isButtonClicked by remember {
        mutableStateOf(false)
    }

    if(isButtonClicked)
    {
        LaunchedEffect(animatedProgressX) {
            animatedProgressX.animateTo(1f,
                animationSpec = tween(
                    durationMillis = 2000,
                    delayMillis = 1000
                ))
        }

        LaunchedEffect(animatedProgressY) {
            animatedProgressY.animateTo(1f,
                animationSpec = tween(
                    durationMillis = 2000,
                    delayMillis = 1000
                ))
        }

        LaunchedEffect(animatedTranslateY) {
            animatedTranslateY.animateTo(300f,
                animationSpec = tween(
                    durationMillis = 2000,
                    delayMillis = 1000
                ))
        }
    }

    else
    {
        LaunchedEffect(animatedProgressX) {
            animatedProgressX.animateTo(1.5f,
                animationSpec = tween(
                    durationMillis = 2000,
                    delayMillis = 1000
                ))
        }

        LaunchedEffect(animatedProgressY) {
            animatedProgressY.animateTo(1.5f,
                animationSpec = tween(
                    durationMillis = 2000,
                    delayMillis = 1000
                ))
        }

        LaunchedEffect(animatedTranslateY) {
            animatedTranslateY.animateTo(-300f,
                animationSpec = tween(
                    durationMillis = 2000,
                    delayMillis = 1000
                ))
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                //.offset(animatedXOffset, animatedYOffset)

        ) {
            Image(
                painter = painterResource(R.drawable.sample_image),
                contentDescription = null,
                modifier = Modifier
                .graphicsLayer {
                translationX =animatedTranslateY.value
                scaleX = animatedProgressX.value
                scaleY = animatedProgressY.value
            }
            )

        }

    }

    Button(onClick = {

        isButtonClicked = !isButtonClicked

    }) {
        Text("Move Image")
    }


}