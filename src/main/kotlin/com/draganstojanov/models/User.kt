package com.draganstojanov.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val nickName: String = "",
    val email: String,
    val status: Int = 0
)

val userStorage = mutableListOf<User>()
