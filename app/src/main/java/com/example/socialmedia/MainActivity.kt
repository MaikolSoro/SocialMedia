package com.example.socialmedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.BorderAll
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.socialmedia.ui.screens.HomeScreen
import com.example.socialmedia.ui.theme.SocialMediaTheme
import com.example.socialmedia.ui.theme.facebookBlue
import com.example.socialmedia.ui.theme.facebookDarkGray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialMediaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SocialMediaContent()
                }
            }
        }
    }
}


@Composable
fun SocialMediaContent() {
    // Banner
    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreen()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .align(Alignment.BottomCenter)
                .shadow(elevation = 20.dp, shape = CircleShape)
            //.background(MaterialTheme.colorScheme.background)
        ) {
            val iconsColor = facebookDarkGray
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .selectableGroup()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.97f)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                Icon(
                    imageVector = Icons.Rounded.Home, contentDescription = null, tint = facebookBlue
                )
                Icon(
                    imageVector = Icons.Rounded.PlayCircle,
                    contentDescription = null,
                    tint = iconsColor
                )
                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = null,
                    tint = iconsColor
                )
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null,
                    tint = iconsColor
                )
                Icon(
                    imageVector = Icons.Rounded.BorderAll,
                    contentDescription = null,
                    tint = iconsColor
                )
            }
        }
    }

}
