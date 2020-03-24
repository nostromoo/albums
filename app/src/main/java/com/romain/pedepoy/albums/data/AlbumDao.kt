package com.romain.pedepoy.albums.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    fun getAll(): LiveData<List<Album>>

    @Query("SELECT * FROM albums")
    fun getPagedList(): DataSource.Factory<Int, Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<Album>)

}