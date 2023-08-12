package com.example.socialmedia.ui.screens.user_profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.socialmedia.data.models.UserList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(): ViewModel() {

    val users = mutableStateOf(UserList.mock)
    val currentUser = mutableStateOf(users.value.first())
}