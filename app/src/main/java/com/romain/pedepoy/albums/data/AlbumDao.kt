package com.romain.pedepoy.albums.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album")
    fun getAll(): Array<Album>

    @Insert
    fun insertAll(vararg users: Array<Album>)

}