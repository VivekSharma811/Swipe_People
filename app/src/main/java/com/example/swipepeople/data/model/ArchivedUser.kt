package com.example.swipepeople.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "People")
data class ArchivedUser(
    val name: String,
    @PrimaryKey val profilePic: String,
    val phone: String
)