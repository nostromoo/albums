package com.romain.pedepoy.albums.service

import com.romain.pedepoy.albums.data.Album
import retrofit2.Response
import retrofit2.http.GET


interface AlbumApi {
    @GET("/img/shared/technical-test.json")
    suspend fun getAlbums(): Response<List<Album>>
}