package com.romain.pedepoy.albums.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.romain.pedepoy.albums.service.AlbumApi
import com.romain.pedepoy.albums.utilities.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AlbumRepository  constructor(private val albumDao: AlbumDao) {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val webservice : AlbumApi = retrofit.create<AlbumApi>(AlbumApi::class.java) //todo utiliser Dagger pour injecter le WS

    fun getAlbums(): LiveData<List<Album>> {

        val data = MutableLiveData<List<Album>>()
        webservice.getAlbums().enqueue(object :
            Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                response.let {
                    data.value = response.body()

                    runBlocking {
                        withContext(Dispatchers.Default){
                            insertAlbumsInDatabase(response.body()!!
                            )}
                    }
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                data.value = albumDao.getAll().value
            }
        })
        return data
    }

    suspend fun insertAlbumsInDatabase(albums: List<Album>) {
        albumDao.insertAll(albums)
    }


    companion object {

        @Volatile private var instance: AlbumRepository? = null

        fun getInstance(albumDao: AlbumDao) =
            instance ?: synchronized(this) {
                instance ?: AlbumRepository(albumDao).also { instance = it }
            }
    }
}