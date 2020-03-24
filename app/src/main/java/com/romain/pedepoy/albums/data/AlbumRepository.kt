package com.romain.pedepoy.albums.data

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.romain.pedepoy.albums.service.AlbumApi
import com.romain.pedepoy.albums.utilities.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumRepository  constructor(private val albumDao: AlbumDao, private val remoteSource: AlbumsRemoteDataSource) {

    val albums = resultLiveData(
        databaseQuery = {
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(INITIAL_LOAD)
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(PRE_FETCH_DISTANCE)
                .build()
            LivePagedListBuilder<Int, Album>(albumDao.getPagedList(), config).build()
        },
        networkCall = { remoteSource.fetchData() },
        saveCallResult = { albumDao.insertAll(it) }
    )


    companion object {

        const val INITIAL_LOAD = 100
        const val PAGE_SIZE = 50
        const val PRE_FETCH_DISTANCE = 10

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