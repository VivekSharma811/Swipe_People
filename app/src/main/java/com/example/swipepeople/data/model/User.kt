package com.example.swipepeople.data.model

data class User(
    val SSN: String,
    val cell: String,
    val dob: String,
    val email: String,
    val gender: String,
    val location: Location,
    val md5: String,
    val name: Name,
    val password: String,
    val phone: String,
    val picture: String,
    val registered: String,
    val salt: String,
    val sha1: String,
    val sha256: String,
    val username: String
)