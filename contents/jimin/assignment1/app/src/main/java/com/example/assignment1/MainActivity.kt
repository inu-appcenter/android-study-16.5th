package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
                        composable("main_screen") { MainScreen(navController) }
                        composable("secondary_screen") { SecondaryScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(navController)
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
            val stories = listOf(
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11056f1df7cc58f605ce8029a84269fa.jpg", name = "ehsan"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11945081a5b36eebba0679f61dfbd225.jpg", name = "melika"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11458b284947739865cd857e828f1f21.jpg", name = "jax"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen4f59c3a3f9487457d506860bb75e3247.jpg", name = "sara"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen116a503718103ee85aa48038cc85d079.jpg", name = "marta"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11670e8d5aee2f5eacc036906962f823.jpg", name = "niki"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gena157df185052c72cc24fec571a77cdf0.jpg", name = "john"),
            )
            Stories(stories = stories)
        }
    }
}

@Composable
fun SecondaryScreen() {
    Scaffold(
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            val stories = listOf(
                User(profile = "data:image/png;base64,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", name = "ehsan"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11945081a5b36eebba0679f61dfbd225.jpg", name = "melika"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11458b284947739865cd857e828f1f21.jpg", name = "jax"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen4f59c3a3f9487457d506860bb75e3247.jpg", name = "sara"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen116a503718103ee85aa48038cc85d079.jpg", name = "marta"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11670e8d5aee2f5eacc036906962f823.jpg", name = "niki"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gena157df185052c72cc24fec571a77cdf0.jpg", name = "john"),
            )
            Stories2(stories = stories)
        }
    }

}

@Composable
fun Stories(stories:List<User>) {
    val context = LocalContext.current
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        itemsIndexed(stories){index,story->
            if (index == 0){
                Spacer(modifier = Modifier.width(10.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier
                    .border(
                        2.dp, Brush.horizontalGradient(
                            listOf(
                                Color(0xffff6f00),
                                Color(0xffffeb35),
                                Color(0xffff6f00),
                                Color(0xffff2b99),
                                Color(0xffff2bd1),
                                Color(0xffff2bd1),
                            )
                        ), CircleShape
                    )
                    .size(70.dp),
                    contentAlignment = Alignment.Center
                ){
                    AsyncImage(
                        model = ImageRequest
                            .Builder(context)
                            .data(story.profile)
                            .crossfade(400)
                            .build(),
                        modifier= Modifier
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
fun Stories2(stories:List<User>) {
    val context = LocalContext.current
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        itemsIndexed(stories){index,story->
            if (index == 0){
                Spacer(modifier = Modifier.width(10.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier

                ){
                    AsyncImage(
                        model = ImageRequest
                            .Builder(context)
                            .data(story.profile)
                            .crossfade(400)
                            .build(),
                        modifier= Modifier
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
fun TopBar(navController: NavController) {
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
                modifier = Modifier.size(23.dp),
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
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {
        bottomBarItems.forEach {
            Icon(
                modifier=Modifier.size(22.dp),
                painter = painterResource(id = it),
                contentDescription = null
            )
        }
        AsyncImage(
            modifier= Modifier
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