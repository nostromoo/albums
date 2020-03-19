package com.romain.pedepoy.albums.service

import com.romain.pedepoy.albums.data.Album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface AlbumService {
    @GET("/img/shared/technical-test.json")
    fun getAlbums(): Call<List<Album>>
}