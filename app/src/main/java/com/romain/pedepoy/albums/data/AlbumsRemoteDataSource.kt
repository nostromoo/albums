package com.romain.pedepoy.albums.data

import com.romain.pedepoy.albums.service.AlbumApi
import com.romain.pedepoy.albums.service.BaseDataSource
import javax.inject.Inject


class AlbumsRemoteDataSource @Inject constructor(private val service: AlbumApi) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getAlbums() }
}
