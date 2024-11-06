package com.example.instagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp // Ensure this import is added
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.instagram.ui.theme.InstagramTheme
import kotlinx.coroutines.delay

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
        enableEdgeToEdge() //동영상에 없는 부분이야
        setContent {
            InstagramTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopBar()
                        },
                        bottomBar = {
                            BottomBar()
                        }
                    ){ paddings->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddings)
                        ){
                            val stories = listOf(
                                User(
                                    profile = "https://this-person-does-not-exist.com/img/avatar-gen533c7b1ccc95806a2c98fa631ce88e13.jpg",
                                    name = "ehsan"
                                ),
                                User(
                                    profile = "https://this-person-does-not-exist.com/img/avatar-genf14363f648adcf5d24738b383fbf5a46.jpg",
                                    name = "melike"
                                ),
                                User(
                                    profile = "https://this-person-does-not-exist.com/img/avatar-gen5186bd9ba69831941e444ec73e74d90b.jpg",
                                    name = "jax"
                                ),
                                User(
                                    profile = "https://this-person-does-not-exist.com/img/avatar-genbd022bf9feec63c451536f4391a9939b.jpg",
                                    name = "sara"
                                ),
                                User(
                                    profile = "https://this-person-does-not-exist.com/img/avatar-gen8c7605579539c49456894cc4f5a791ae.jpg",
                                    name = "marta"
                                ),
                                User(
                                    profile = "https://this-person-does-not-exist.com/img/avatar-gen47613cdb55df065ed5837336e5b5e2db.jpg",
                                    name = "niki"
                                ),
                                User(
                                    profile = "https://this-person-does-not-exist.com/img/avatar-genc5c636ccb26ab8b5dd54f3496c67b41f.jpg",
                                    name = "john"
                                )
                            )
                            val posts = listOf(
                                Post(
                                    user = stories[0],
                                    post = "https://this-person-does-not-exist.com/img/avatar-gena03fdc69de53dc73d0c67c969ca9a163.jpg",
                                    description = "first post",
                                    likesCount = (100..10000).random(),
                                    commentsCount = (100..10000).random()
                                ),
                                Post(
                                    user = stories[1],
                                    post = "https://this-person-does-not-exist.com/img/avatar-gen37e4a63aa0f007f307362b5b37422e50.jpg",
                                    description = "second post",
                                    likesCount = (100..10000).random(),
                                    commentsCount = (100..10000).random()
                                ),
                                Post(
                                    user = stories[2],
                                    post = "https://this-person-does-not-exist.com/img/avatar-gena274f815ae6e59fd9cc57e77dbc91998.jpg",
                                    description = "third post",
                                    likesCount = (100..10000).random(),
                                    commentsCount = (100..10000).random()
                                ),
                                Post(
                                    user = stories[3],
                                    post = "https://this-person-does-not-exist.com/img/avatar-genc86414e97d557a008ff3a3cd9fb79b16.jpg",
                                    description = "fourth post",
                                    likesCount = (100..10000).random(),
                                    commentsCount = (100..10000).random()
                                ),
                                Post(
                                    user = stories[4],
                                    post = "https://this-person-does-not-exist.com/img/avatar-gene2f82d8c0fffb54abffdbd445d3138c7.jpg",
                                    description = "fifth post",
                                    likesCount = (100..10000).random(),
                                    commentsCount = (100..10000).random()
                                ),
                            )
                            Posts(stories = stories, posts = posts)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Posts(
    stories:List<User>,
    posts:List<Post>
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        item{
            Stories(stories = stories)
        }
        items(posts){ post->
            var liked by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(liked) {
                if(liked){
                    delay(2000)
                    liked = false
                }
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 1.dp, horizontal = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Box(modifier = Modifier
                            .border(
                                2.dp, Brush.horizontalGradient(
                                    listOf(
                                        Color(color = 0xffff6f00),
                                        Color(color = 0xffffeb35),
                                        Color(color = 0xffff6f00),
                                        Color(color = 0xffff2b99),
                                        Color(color = 0xffff2bd1),
                                        Color(color = 0xffff2bd1),
                                    )
                                ), CircleShape
                            )
                            .size(33.dp),
                            contentAlignment = Alignment.Center
                        ){
                            AsyncImage(
                                model = ImageRequest
                                    .Builder(context)
                                    .data(post.user.profile)
                                    .crossfade(400)
                                    .build(),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(60.dp),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = post.user.name)
                    }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.pointerInput(Unit){
                detectTapGestures(onDoubleTap = {
                    liked = true
                })
            }, contentAlignment = Alignment.Center){
                AsyncImage(
                    model = ImageRequest
                    .Builder(context)
                    .data(post.post)
                    .crossfade(400)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                )
                AnimatedVisibility(visible = liked, enter = scaleIn(spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )), exit = scaleOut()
                ) {
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
            ){
                val iconModifier = Modifier.size(20.dp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    Icon(
                        modifier = iconModifier,
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null
                    )
                    Icon(
                        modifier = iconModifier,
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = null
                    )
                    Icon(
                        modifier = iconModifier,
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = null
                    )
                }
                Icon(
                    modifier = iconModifier,
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = null
                )

            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(modifier = Modifier.padding(horizontal = 8.dp)){
                Text(text = "${post.likesCount} likes", fontSize = 13.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = post.description, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "View all ${post.commentsCount} comments",
                    color = Color.Gray,
                    fontSize = 20.sp
                )
            }
        }
    }
}


@Composable
fun Stories(stories:List<User>){
    val context = LocalContext.current
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        itemsIndexed(stories){index, story ->
            if(index == 0){
                Spacer(modifier = Modifier.width(10.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Box(modifier = Modifier
                    .border(
                        2.dp, Brush.horizontalGradient(
                            listOf(
                                Color(color = 0xffff6f00),
                                Color(color = 0xffffeb35),
                                Color(color = 0xffff6f00),
                                Color(color = 0xffff2b99),
                                Color(color = 0xffff2bd1),
                                Color(color = 0xffff2bd1),
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
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun TopBar() {
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
            contentDescription = "Instagram"
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
                modifier = Modifier.size(23.dp),
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = null
            )
        }
    }
}

@Composable
fun BottomBar(){
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
    horizontalArrangement = Arrangement.SpaceAround){
        bottomBarItems.forEach{
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
                .data("https://this-person-does-not-exist.com/img/avatar-gend60b2225ff0541a89d1a233913e3e019.jpg")
                .crossfade(400)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop

        )
    }
}