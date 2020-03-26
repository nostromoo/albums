package com.romain.pedepoy.albums.data

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepository  @Inject constructor(private val albumDao: AlbumDao,
                                           private val remoteSource: AlbumsRemoteDataSource) {

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

        const val INITIAL_LOAD = 60
        const val PAGE_SIZE = 20
        const val PRE_FETCH_DISTANCE = 10

    }
}