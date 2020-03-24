package com.romain.pedepoy.albums.data

import com.romain.pedepoy.albums.service.AlbumApi
import com.romain.pedepoy.albums.utilities.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumRepository  constructor(private val albumDao: AlbumDao, private val remoteSource: AlbumsRemoteDataSource) {

    val albums = resultLiveData(
        databaseQuery = {
            albumDao.getAll()
        },
        networkCall = { remoteSource.fetchData() },
        saveCallResult = {
            albumDao.insertAll(it)
        })


    companion object {

        @Volatile private var instance: AlbumRepository? = null

        fun getInstance(albumDao: AlbumDao) =
            instance ?: synchronized(this) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val webservice : AlbumApi = retrofit.create<AlbumApi>(AlbumApi::class.java)
                instance ?: AlbumRepository(albumDao, AlbumsRemoteDataSource(webservice) ).also { instance = it }
            }
    }
}