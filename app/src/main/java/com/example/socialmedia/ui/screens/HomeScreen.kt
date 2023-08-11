package com.example.socialmedia.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.socialmedia.R
import com.example.socialmedia.data.models.Poster
import com.example.socialmedia.data.models.User
import com.example.socialmedia.ui.theme.facebookBlue
import com.example.socialmedia.ui.theme.facebookFucsiaColor
import com.example.socialmedia.ui.theme.facebookGray
import com.example.socialmedia.ui.theme.facebookGreenColor
import com.example.socialmedia.ui.theme.facebookTextGrayColor
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook


val avatarModifier = Modifier
    .size(40.dp)
    .clip(CircleShape)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    val currentUser = homeViewModel.currentUser.value
    val users = homeViewModel.users.value
    val posters = homeViewModel.posters.value

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                navigationIcon = {
                    // Icon
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = FontAwesomeIcons.Brands.Facebook,
                        contentDescription = null,
                        tint = facebookBlue
                    )
                }, title = {},
                actions = {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(facebookGray)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }

                    Spacer(modifier = Modifier.size(14.dp))
                    LoadImage(
                        url = currentUser.avatar, modifier = avatarModifier
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(50))
                            .background(facebookGray),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 14.dp),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp,
                            style = MaterialTheme.typography.bodyMedium,
                            color = facebookTextGrayColor,
                            text = stringResource(id = R.string.home_message_text)
                        )
                        /* BasicTextField(
                            modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                           textStyle = MaterialTheme.typography.bodyMedium,
                           value = "What's on your mind?",
                           onValueChange = { "" }
                          )
                         */
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {

                        CommonButton(
                            color = facebookFucsiaColor,
                            text = "Live",
                            icon = Icons.Default.Camera,
                            onClick = {}
                        )

                        CommonButton(
                            color = facebookGreenColor,
                            text = "Photo",
                            icon = Icons.Default.Image,
                            onClick = {}
                        )

                        CommonButton(
                            color = facebookGray,
                            width = 50.dp,
                            icon = Icons.Default.MoreHoriz,
                            tintColor = Color.Black,
                            onClick = {}
                        )
                    }
                }
            }
            // Story
            item {
                StoryBoard(currentUser = currentUser, userList = users)
            }

            items(posters) { item: Poster ->
                CardPoster(item)
            }

        }
    }
}


@Composable
fun CardPoster(item: Poster) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {

            // HeaderPost
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(modifier = Modifier) {
                    LoadImage(
                        modifier = avatarModifier,
                        url = item.user.avatar
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Column(modifier = Modifier) {
                        Text(
                            text = item.user.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Row(modifier = Modifier) {
                            Text(
                                text = item.time,
                                color = facebookTextGrayColor,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Default.People,
                                contentDescription = null
                            )
                        }
                    }
                }

            }

            // Content
        }
    }
}

@Composable
fun StoryBoard(currentUser: User, userList: List<User>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Story",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(10.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                StoryCard(
                    bannerImage = currentUser.bannerImage,
                    title = "Create Story",
                    color = facebookBlue
                )
            }
            items(userList) { user ->
                StoryCard(
                    avatarImage = user.avatar,
                    bannerImage = user.bannerImage,
                    title = user.name,
                    textColor = Color.Black
                )

            }
            item {
                Spacer(modifier = Modifier.size(10.dp))
            }
        }

    }
}

@Composable
fun StoryCard(
    bannerImage: String,
    avatarImage: String? = null,
    title: String,
    color: Color = Color.White,
    textColor: Color = Color.White
) {

    Card(
        modifier = Modifier
            .height(170.dp)
            .width(90.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color),
        backgroundColor = color,
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LoadImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                url = bannerImage
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(50.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (avatarImage == null) {
                    // CHECK the SHADOW
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = facebookBlue
                        )
                    }
                } else {

                    LoadImage(
                        modifier = avatarModifier.then(
                            Modifier.border(2.dp, color = Color.White, shape = CircleShape)
                        ),
                        url = avatarImage
                    )
                }
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Clip,
                    maxLines = 2,
                    color = textColor
                )
            }

        }
    }
}

@Composable
fun CommonButton(
    color: Color = Color.Red,
    tintColor: Color = Color.White,
    icon: ImageVector? = null,
    text: String? = null,
    width: Dp = 130.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(30.dp)
            .width(width)
            .shadow(elevation = 20.dp, shape = RoundedCornerShape(20.dp), spotColor = color)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .height(30.dp)
                .width(width)
                .clip(CircleShape)
                .background(color = color)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                icon?.let {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = icon,
                        contentDescription = null,
                        tint = tintColor
                    )

                }
                text?.let {
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = text, color = tintColor)
                }
            }
        }
    }
}

@Composable
fun LoadImage(modifier: Modifier, url: String) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        visible = true,
                        color = Color.White,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = facebookGray)
                    )
            )
        }
    )
}
