package com.example.socialmedia.ui.screens.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.socialmedia.data.models.User
import com.example.socialmedia.ui.theme.SocialMediaTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(userProfileViewModel: UserProfileViewModel = hiltViewModel()) {

    val currentUser = userProfileViewModel.currentUser.value

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                // Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(padding)
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.Blue),
                        model = currentUser.bannerImage,
                        contentDescription = "banner",
                        loading = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                // TODO: CHANGE FOR SHIMMER EFFECT
                                CircularProgressIndicator()
                            }
                        }
                    )

                    IconButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search, contentDescription = "Search"
                        )
                    }

                    Banner(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), currentUser = currentUser
                    )
                }

            }

            item {
                //Name and followers
            }
        }
    }
}


@Composable
fun Banner(currentUser: User, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {

        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            model = currentUser.bannerImage,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            loading = {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            onClick = { /* TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "Search"
            )
        }
        AvatarImg(
            currentUser.avatar,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 13.dp)
                .blur(5.dp)
                .alpha(0.3f),
            showBadge = false,
        )

        AvatarImg(
            currentUser.avatar,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomCenter),
            showBadge = true,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarImg(avatar: String, modifier: Modifier, showBadge: Boolean = false) {
    Box(
        modifier = modifier

    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, color = Color.White, shape = CircleShape)
                .clip(CircleShape),
            model = avatar,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        if (showBadge)
            Badge(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .border(1.dp, color = Color.White, shape = CircleShape),
                containerColor = Color.Green,
                content = {
                    Box(modifier = Modifier.size(5.dp))
                })
    }
}

@Preview
@Composable
fun UserProfilePreview() {
    SocialMediaTheme {
        UserProfileScreen()
    }
}
