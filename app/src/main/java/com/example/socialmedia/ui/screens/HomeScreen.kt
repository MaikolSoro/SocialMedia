package com.example.socialmedia.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.socialmedia.R
import com.example.socialmedia.ui.theme.facebookBlue
import com.example.socialmedia.ui.theme.facebookFucsiaColor
import com.example.socialmedia.ui.theme.facebookGray
import com.example.socialmedia.ui.theme.facebookGreenColor
import com.example.socialmedia.ui.theme.facebookTextGrayColor
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
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
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                navigationIcon = {
                    // Icon
                    Icon(
                        modifier = Modifier.size(20.dp),
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
                            .height(40.dp)
                            .padding(horizontal = 12.dp)
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
        }
    }
}

@Composable
fun CommonButton(
    color: Color = Color.Red,
    tintColor: Color = Color.White,
    icon: ImageVector? = null,
    text: String? = null,
    width: Dp = 120.dp,
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
