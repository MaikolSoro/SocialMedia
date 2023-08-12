package com.example.socialmedia.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.socialmedia.data.models.PostersList
import com.example.socialmedia.data.models.UserList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():ViewModel() {

    val users = mutableStateOf(UserList.mock)
    val currentUser = mutableStateOf(users.value.random())
    val posters = mutableStateOf(PostersList.mock)
}