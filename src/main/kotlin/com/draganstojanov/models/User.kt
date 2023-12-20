package com.draganstojanov.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val nickName: String = "",
    val email: String,
    val status: Int = 0
)

val userStorage = mutableListOf<User>()


//{
//    "id": "100",
//    "firstName": "Jane",
//    "lastName": "Smith",
//    "email": "jane.smith@company.com"
//    "nickName" "Jane1",
//    "status":"100"
//}