package com.romain.pedepoy.albums.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album (
    val albumId: Int,
    @PrimaryKey val Id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val url: String,
    @ColumnInfo val thumbnailUrl: String
)