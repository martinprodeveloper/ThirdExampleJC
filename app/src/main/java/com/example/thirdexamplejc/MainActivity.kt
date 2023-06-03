package com.example.thirdexamplejc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thirdexamplejc.model.Routes
import com.example.thirdexamplejc.ui.theme.ThirdExampleJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirdExampleJCTheme {
                // A surface container using the 'background' color from the theme
                var selected by remember { mutableStateOf("Ejemplo 1") }
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*val myOptions = getOptions(listOf("Martin", "Ejemplo", "CheckBox"))*/
                    /*Column {
                        /*var myText by remember { mutableStateOf("Martin") }
                        MyTextFieldStateHoisting(myText) { myText = it }*/
                        /*MyTriStatusCheckBox()
                        myOptions.forEach{
                            MyCheckBoxWithTextCompleted(it)
                        }*/
                        /*MyRadioButtonList(selected) {selected = it}*/

                        MyRangeSlider()
                    }*/
                    /*var show by remember { mutableStateOf(false) }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = { show = true }) {
                            Text(text = "Mostrar Dialogo")
                        }
                        /*MyAlertDialog(
                            show = show,
                            onDismiss = { show = false },
                            onConfirm = { Log.i("MyDialog", "Click") }
                        )*/
                        /*MySimpleCustomDialog(
                            show = show,
                            onDismiss = { show = false }
                        )*/
                        /*MyConfirmationDialog(
                            show = show,
                            onDismiss = { show = false }
                        )*/
                    }*/

                    /*val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla1.route
                    ) {
                        composable(Routes.Pantalla1.route) { Screen1(navigationController) }
                        composable(Routes.Pantalla2.route) { Screen2(navigationController) }
                        composable(Routes.Pantalla3.route) { Screen3(navigationController) }
                        composable(
                            Routes.Pantalla4.route,
                            arguments = listOf(navArgument("age") { type = NavType.IntType })
                        ) { backStackEntry ->
                            Screen4(
                                navigationController,
                                backStackEntry.arguments?.getInt("age") ?: 0
                            )
                        }
                        composable(
                            Routes.Pantalla5.route,
                            arguments = listOf(navArgument("name") { defaultValue = "pepe" })
                        ) { backStackEntry ->
                            Screen5(
                                navigationController,
                                backStackEntry.arguments?.getString("name")
                            )
                        }
                    }*/

                    CrossfadeExample()
                }
            }
        }
    }
}

@Composable
fun MyText() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Esto es un ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Esto es un ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.LineThrough, TextDecoration.Underline)
                )
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}

@Composable
fun MyTextField() {

    var myText by remember { mutableStateOf("Martin") }

    TextField(value = myText, onValueChange = { myText = it })
}

@Composable
fun MyTextFieldAdvanced() {

    var myText by remember { mutableStateOf("") }

    TextField(value = myText, onValueChange = {
        myText = if (it.contains("a")) {
            it.replace("a", "")
        } else {
            it
        }
    }, label = { Text(text = "Introduce tu nombre") })
}

@Composable
fun MyTextFieldOutlined() {

    var myText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Hola") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Cyan, unfocusedBorderColor = Color.Red
        )
    )
}

@Composable
fun MyTextFieldStateHoisting(name: String, onValueChanged: (String) -> Unit) {

    TextField(value = name, onValueChange = { onValueChanged(it) })
}

@Composable
fun MyButtonExample() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { Log.d("MyButtonExample", "Esto es un ejemplo") },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow, contentColor = Color.Blue
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Hola")
        }
    }
}

@Composable
fun MyButtonState() {
    var enabled by rememberSaveable { mutableStateOf(true) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enabled = false }, enabled = enabled, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow, contentColor = Color.Blue
            ), border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Hola")
        }

        OutlinedButton(
            onClick = { enabled = false }, enabled = enabled, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow,
                contentColor = Color.Blue,
                disabledBackgroundColor = Color.Gray,
                disabledContentColor = Color.Red
            )
        ) {
            Text(text = "Hola")
        }

        TextButton(onClick = { }) {
            Text(text = "Hola")
        }
    }
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Ejemplo",
        alpha = 0.5f
    )
}

@Composable
fun MyImageAdvanced() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Ejemplo",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Blue, CircleShape)
    )
}

@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.StarRate, contentDescription = "Icono", tint = Color.Green
    )
}

@Composable
fun MyProgress() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.Magenta, strokeWidth = 5.dp)
        LinearProgressIndicator(
            modifier = Modifier.padding(top = 36.dp),
            color = Color.Magenta,
            backgroundColor = Color.Yellow
        )
    }
}

@Composable
fun MyProgressItermediate() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Magenta, strokeWidth = 5.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 36.dp),
                color = Color.Magenta,
                backgroundColor = Color.Yellow
            )
        }

        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar Progress")
        }
    }
}

@Composable
fun MyProgressAdvanced() {
    var progressStatus by rememberSaveable { mutableStateOf(0f) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progressStatus)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp), horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { progressStatus += 0.1f }) {
                Text(text = "Incrementar")
            }
            Button(onClick = { progressStatus -= 0.1f }) {
                Text(text = "Reducir")
            }
        }
    }
}

@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(true) }

    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Green,
            uncheckedTrackColor = Color.Magenta,
            checkedTrackColor = Color.Cyan,
            uncheckedTrackAlpha = 0.1f,
            checkedTrackAlpha = 0.1f,
            disabledCheckedThumbColor = Color.Yellow,
            disabledCheckedTrackColor = Color.Yellow,
            disabledUncheckedThumbColor = Color.Blue,
            disabledUncheckedTrackColor = Color.Blue
        )
    )
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(false) }

    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Green, uncheckedColor = Color.Red, checkmarkColor = Color.Blue
        )
    )
}

@Composable
fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.width(size.dp))
}

@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable { mutableStateOf(false) }

    Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        MySpacer(size = 10)
        Text(text = "Ejemplo 1 Check Box")
    }

}

@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {
    Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        MySpacer(size = 10)
        Text(text = checkInfo.title)
    }

}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus -> status = myNewStatus })
    }
}

@Composable
fun MyTriStatusCheckBox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }

    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = true, onClick = { }, enabled = false, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledColor = Color.Green
            )
        )
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {

    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 1", onClick = { onItemSelected("Ejemplo 1") })
            Text(text = "Ejemplo 1")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 2", onClick = { onItemSelected("Ejemplo 2") })
            Text(text = "Ejemplo 2")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 3", onClick = { onItemSelected("Ejemplo 3") })
            Text(text = "Ejemplo 3")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 4", onClick = { onItemSelected("Ejemplo 4") })
            Text(text = "Ejemplo 4")
        }
    }
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.LightGray,
        contentColor = Color.Blue,
        border = BorderStroke(5.dp, Color.Green)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 2")
            Text(text = "Ejemplo 3")
        }
    }
}

@Composable
fun MyBadgeBox() {
    BadgedBox(modifier = Modifier.padding(20.dp), badge = {
        Badge(
            content = {
                Text(modifier = Modifier.padding(2.dp), text = "10")
            },
            backgroundColor = Color.Black,
            contentColor = Color.White,
            modifier = Modifier.padding(2.dp)
        )
    }) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Default.Star,
            contentDescription = "l",
            tint = Color.Blue
        )
    }
}

@Composable
fun MyDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), color = Color.Green
    )
}

@Composable
fun MyDropDownMenu() {

    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val desserts = listOf("Chocolate", "Helado", "Cacao", "Café")

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth())
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedText = dessert
                }) {
                    Text(text = dessert)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThirdExampleJCTheme {
        //MyText()
        //MyTextField()
        //MyTextFieldAdvanced()
        //MyTextFieldOutlined()
        //MyTextFieldStateHoisting(name = "Hola", onValueChanged = {})
        //MyButtonExample()
        //MyButtonState()
        //MyImage()
        //MyImageAdvanced()
        //MyIcon()
        //MyProgress()
        //MyProgressItermediate()
        //MyProgressAdvanced()
        //MySwitch()
        //MyCheckBox()
        //MyCheckBoxWithText()
        //MyTriStatusCheckBox()
        //MyRadioButton()
        //MyRadioButtonList(name = "Martin", onItemSelected = {})
        //MyCard()
        //MyBadgeBox()
        //MyDivider()
        MyDropDownMenu()
    }
}