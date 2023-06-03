package com.example.thirdexamplejc

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SensorDoor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt

@Composable
fun ColorAnimationSimple() {
    var firstColor by rememberSaveable { mutableStateOf(false) }
    var realColor1 = if (firstColor) Color.Blue else Color.Yellow

    var secondColor by rememberSaveable { mutableStateOf(false) }
    val realColor2 by animateColorAsState(targetValue = if (secondColor) Color.Blue else Color.Yellow)

    Column() {
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor1)
            .clickable { firstColor = !firstColor }
        )

        Spacer(modifier = Modifier.size(200.dp))

        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor2)
            .clickable { secondColor = !secondColor }
        )
    }
}

@Composable
fun ColorAnimationAdvanced() {
    var showBox by rememberSaveable { mutableStateOf(true) }
    var firstColor by rememberSaveable { mutableStateOf(false) }
    val realColor1 by animateColorAsState(
        targetValue = if (firstColor) Color.Blue else Color.Yellow,
        animationSpec = tween(durationMillis = 2000),
        finishedListener = { showBox = false }
    )

    if (showBox) {
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor1)
            .clickable { firstColor = !firstColor }
        )
    }

}

@Composable
fun SizeAnimation() {
    var smallSize by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(targetValue = if (smallSize) 50.dp else 100.dp)

    Box(modifier = Modifier
        .size(size)
        .background(Color.Blue)
        .clickable { smallSize = !smallSize }
    )
}

@Composable
fun SizeAnimationAdvanced() {
    var smallSize by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 2000),
        finishedListener = {if (!smallSize) {} }
    )

    Box(modifier = Modifier
        .size(size)
        .background(Color.Blue)
        .clickable { smallSize = !smallSize }
    )
}

@Composable
fun VisibilityAnimation(){
    var isVisible by remember { mutableStateOf(true) }

    Column(Modifier.fillMaxSize()) {

        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Mostrar/Ocultar")
        }

        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility (isVisible, enter = slideInHorizontally(), exit = slideOutHorizontally()){
            Box(modifier = Modifier
                .size(150.dp)
                .background(Color.Blue))
        }

    }
}

@Composable
fun CrossfadeExample(){
    var myComponentType: ComponentType by remember { mutableStateOf(ComponentType.Text) }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        
        Button(onClick = { myComponentType = getComponentTypeRandom() }) {
            Text(text = "Cambiar componente")
        }
        
        Crossfade(targetState = myComponentType) { myComponentType ->
            when(myComponentType){
                ComponentType.Image -> Icon( Icons.Default.SensorDoor, contentDescription = "")
                ComponentType.Text -> Text(text = "Text component")
                ComponentType.Box -> Box(modifier = Modifier
                    .size(150.dp)
                    .background(Color.Blue))
                ComponentType.Error -> Text(text = "Error")
            }
        }
    }
}

enum class ComponentType(){
    Image, Text, Box, Error
}

fun getComponentTypeRandom(): ComponentType{
    return when(nextInt(from = 0, until = 3)){
        0 -> ComponentType.Image
        1 -> ComponentType.Text
        2 -> ComponentType.Box
        else -> ComponentType.Error
    }
}