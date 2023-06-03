package com.example.thirdexamplejc

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdexamplejc.model.Superhero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Martin", "Juan", "Luis")

    LazyColumn {
        item { Text(text = "Header") }
        items(7) {
            Text(text = "Este es el item $it")
        }
        items(myList) {
            Text(text = "Hola me llamo $it")
        }
        item { Text(text = "Footer") }
    }
}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero) {
                Toast.makeText(context, it.superheroName, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(getSuperheroes()) { superhero ->
                ItemHero(superhero = superhero) {
                    Toast.makeText(context, it.superheroName, Toast.LENGTH_LONG).show()
                }
            }
        }, contentPadding = PaddingValues(all = 16.dp)
    )
}

@Composable
fun SuperHeroWithSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutinesScope = rememberCoroutineScope()

    Column() {
        LazyColumn(
            state = rvState, verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperheroes()) { superhero ->
                ItemHero(superhero = superhero) {
                    Toast.makeText(context, it.superheroName, Toast.LENGTH_LONG).show()
                }
            }
        }

        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        rvState.firstVisibleItemScrollOffset

        if (showButton) {
            Button(
                onClick = {
                    coroutinesScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(text = "Button RV")
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superhero: Map<String, List<Superhero>> = getSuperheroes().groupBy { it.publisher }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        superhero.forEach { (publisher, mySuperHero) ->

            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            items(mySuperHero) { superhero ->
                ItemHero(superhero = superhero) {
                    Toast.makeText(context, it.superheroName, Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}

@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Blue),
        modifier = Modifier
            //.width(200.dp)
            .fillMaxWidth()
            .clickable { onItemSelected(superhero) }) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }
    }
}

fun getSuperheroes(): List<Superhero> {
    return listOf(
        Superhero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        Superhero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        Superhero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        Superhero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
        Superhero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        Superhero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        Superhero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman)
    )
}