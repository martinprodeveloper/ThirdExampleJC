package com.example.thirdexamplejc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScaffoldExample() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopAppBar (onClickIcon = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "Has pulsado $it"
                    )
                }
            }, onClickDrawer = {coroutineScope.launch { scaffoldState.drawerState.open() }})
        },
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation() },
        floatingActionButton = { MyFab() },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,
        drawerContent = { MyDrawer {coroutineScope.launch { scaffoldState.drawerState.close() }} },
        drawerGesturesEnabled = false
    ) {

    }
}

@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(title = { Text(text = "Mi primer toolbar") },
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
            IconButton(onClick = { onClickIcon("Peligro") }) {
                Icon(imageVector = Icons.Filled.Dangerous, contentDescription = "dangerous")
            }
        })
}

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableStateOf(0) }

    BottomNavigation(backgroundColor = Color.Blue, contentColor = Color.White) {
        BottomNavigationItem(
            selected = index == 0, onClick = { index = 0 },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "home")
            },
            label = { Text(text = "Home") }
        )

        BottomNavigationItem(
            selected = index == 1, onClick = { index = 1 },
            icon = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "favorite")
            },
            label = { Text(text = "Favorite") }
        )

        BottomNavigationItem(
            selected = index == 2, onClick = { index = 2 },
            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "person")
            },
            label = { Text(text = "Person") }
        )
    }
}

@Composable
fun MyFab() {
    FloatingActionButton(
        onClick = { },
        backgroundColor = Color.Yellow,
        contentColor = Color.Black
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Primer opción",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )

        Text(
            text = "Segunda opción",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )

        Text(
            text = "Tercera opción",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )

        Text(
            text = "Cuarta opción",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
    }
}