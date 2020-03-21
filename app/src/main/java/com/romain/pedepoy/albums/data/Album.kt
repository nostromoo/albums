package com.romain.pedepoy.albums.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album (
    val albumId: Int,
    @PrimaryKey val Id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)