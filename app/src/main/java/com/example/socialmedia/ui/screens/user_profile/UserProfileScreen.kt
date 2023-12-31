package com.example.socialmedia.ui.screens.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.PersonAddAlt
import androidx.compose.material.icons.rounded.Textsms
import androidx.compose.material3.Badge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.socialmedia.data.models.User
import com.example.socialmedia.ui.screens.home.CommonButton
import com.example.socialmedia.ui.screens.home.StoryCard
import com.example.socialmedia.ui.theme.SocialMediaTheme
import com.example.socialmedia.ui.theme.facebookBlue
import com.example.socialmedia.ui.theme.facebookDarkGray
import com.example.socialmedia.ui.theme.facebookGray
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Instagram


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(userProfileViewModel: UserProfileViewModel = hiltViewModel()) {

    val currentUser = userProfileViewModel.currentUser.value
    val cantFriends = remember {
        derivedStateOf {
            currentUser.friends.size
        }
    }
    val userOnlyName = remember {
        derivedStateOf {
            currentUser.name.split(" ").first()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            item {
                // Banner
                Banner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), currentUser = currentUser
                )
            }

            item {
                //Name and followers
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = currentUser.name,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = currentUser.work,
                            style = MaterialTheme.typography.labelMedium,
                            color = facebookDarkGray
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {

                            Text(text = "${currentUser.fallowers} fallowers")
                            Text(text = "${cantFriends.value} friends")

                        }

                        //Buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            CommonButton(
                                icon = Icons.Rounded.PersonAddAlt,
                                color = facebookBlue,
                                text = "Add friends",
                                width = 130.dp
                            ) {
                                //OnClick
                            }
                            CommonButton(
                                icon = Icons.Rounded.Textsms,
                                color = facebookGray,
                                tintColor = Color.Black,
                                text = "Message",
                                width = 130.dp
                            ) {
                                //OnClick
                            }

                            CommonButton(
                                icon = Icons.Rounded.MoreHoriz,
                                color = facebookGray,
                                width = 50.dp,
                                tintColor = Color.Black,
                                onClick = {}
                            )

                        }

                    }
                }
            }

            item {
                //Data the user

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(facebookGray)
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        CustomTextWithBold(
                            currentUser,
                            icon = Icons.Default.LocationOn
                        )
                        //TextIcon(text = currentUser.address, icon = Icons.Default.LocationOn)
                        TextIcon(
                            text = currentUser.instagram,
                            fontWeight = FontWeight.Bold,
                            icon = FontAwesomeIcons.Brands.Instagram
                        )
                        TextIcon(
                            text = "See full ${userOnlyName.value}'s About info",
                            icon = Icons.Default.Person,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }

            item {
                //Friends

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 22.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Friends",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${currentUser.friends.size} - 10 Mutual friends",
                                style = MaterialTheme.typography.labelMedium,
                                color = facebookDarkGray
                            )
                        }

                        CommonButton(
                            text = "See all friends",
                            color = facebookGray,
                            tintColor = MaterialTheme.colorScheme.onBackground
                        ) {

                        }

                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        item {
                            Spacer(modifier = Modifier.size(15.dp))
                        }
                        //Mutual Friends
                        items(currentUser.friends) {
                            AvatarImg(
                                avatar = it.avatar,
                                modifier = Modifier
                                    .size(50.dp)
                                    .shadow(elevation = 8.dp, shape = CircleShape)
                            )
                        }
                    }
                }
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    item {
                        Spacer(modifier = Modifier.size(15.dp))
                    }
                    //More friends
                    items(currentUser.friends) {
                        StoryCard(
                            textColor = MaterialTheme.colorScheme.onBackground,
                            avatarImage = it.avatar,
                            title = it.name,
                            bannerImage = it.bannerImage
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun CustomTextWithBold(
    currentUser: User,
    icon: ImageVector,
    tintColor: Color = Color.Gray,
    textColor: Color = MaterialTheme.colorScheme.onBackground
) {
    val formattedText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append("From ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(currentUser.address)
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(26.dp),
            imageVector = icon,
            contentDescription = null,
            tint = tintColor
        )
        Spacer(modifier = Modifier.size(8.dp))

        Text(text = formattedText, color = textColor)

    }
}


@Composable
fun TextIcon(
    text: String,
    fontWeight: FontWeight,
    icon: ImageVector,
    tintColor: Color = Color.Gray,
    textColor: Color = MaterialTheme.colorScheme.onBackground
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(26.dp),
            imageVector = icon,
            contentDescription = null,
            tint = tintColor
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = text, color = textColor, fontWeight = fontWeight)
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
