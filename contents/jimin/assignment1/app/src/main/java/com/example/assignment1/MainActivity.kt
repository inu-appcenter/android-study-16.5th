package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.assignment1.ui.theme.Assignment1Theme
import kotlinx.coroutines.delay
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

data class User(
    val profile: String,
    val name: String
)

data class Post(
    val user: User,
    val post: String,
    val description: String,
    val likesCount: Int,
    val commentsCount: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel: MainViewModel by viewModels()
        setContent {
            Assignment1Theme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "main_screen"
                    ) {
                        composable("main_screen") { MainScreen(navController, viewmodel) }
                        composable("secondary_screen") { SecondaryScreen(viewmodel) }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavController,
    viewmodel: MainViewModel
) {
    val users by viewmodel.users.collectAsState()

    Scaffold(
        topBar = {
            TopBar(navController, viewmodel)
        },
        bottomBar = {
            BottomBar()
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            Stories(stories = users)
        }
    }
}

@Composable
fun SecondaryScreen(
    viewmodel: MainViewModel
) {
    val users by viewmodel.users.collectAsState()

    Scaffold(
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {

            Stories2(stories = users)
        }
    }

}

@Composable
fun Stories(stories: List<User>) {
    val context = LocalContext.current
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(stories) { index, story ->
            if (index == 0) {
                Spacer(modifier = Modifier.width(10.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            2.dp,
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xffff6f00),
                                    Color(0xffffeb35),
                                    Color(0xffff6f00),
                                    Color(0xffff2b99),
                                    Color(0xffff2bd1),
                                    Color(0xffff2bd1),
                                )
                            ),
                            CircleShape
                        )
                        .size(70.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(context)
                            .data(story.profile)
                            .crossfade(400)
                            .build(),
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = story.name, fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}


@Composable
fun Stories2(stories: List<User>) {
    val context = LocalContext.current
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(stories) { index, story ->
            if (index == 0) {
                Spacer(modifier = Modifier.width(10.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier

                ) {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(context)
                            .data(story.profile)
                            .crossfade(400)
                            .build(),
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = story.name, fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun TopBar(navController: NavController, viewmodel: MainViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.height(40.dp),
            painter = painterResource(id = R.drawable.instagram),
            contentDescription = "instagram"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(23.dp)
                    .clickable { viewmodel.addUsers() },
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                modifier = Modifier
                    .size(23.dp)
                    .clickable {
                        navController.navigate("secondary_screen")
                    },
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "search"
            )
        }
    }
}


@Composable
fun BottomBar() {
    val context = LocalContext.current
    val bottomBarItems = listOf(
        R.drawable.ic_home,
        R.drawable.ic_search,
        R.drawable.ic_add,
        R.drawable.ic_ig
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        bottomBarItems.forEach {
            Icon(
                modifier = Modifier.size(22.dp),
                painter = painterResource(id = it),
                contentDescription = null
            )
        }
        AsyncImage(
            modifier = Modifier
                .size(27.dp)
                .clip(CircleShape),
            model = ImageRequest
                .Builder(context)
                .data("https://this-person-does-not-exist.com/img/avatar-gen11add62e724cde7222c445a4af1a61fc.jpg")
                .crossfade(400)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}