package com.romain.pedepoy.albums.data

import com.romain.pedepoy.albums.service.AlbumApi
import com.romain.pedepoy.albums.service.BaseDataSource


class AlbumsRemoteDataSource constructor(private val service: AlbumApi) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getAlbums() }
}
