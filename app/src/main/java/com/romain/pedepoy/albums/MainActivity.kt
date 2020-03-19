package com.romain.pedepoy.albums

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.romain.pedepoy.albums.data.Album
import com.romain.pedepoy.albums.data.AppDatabase
import com.romain.pedepoy.albums.service.AlbumService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "albums"
        ).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://static.leboncoin.fr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: AlbumService = retrofit.create<AlbumService>(AlbumService::class.java)

        val albumsCall: Call<List<Album>> = service.getAlbums()
        albumsCall.enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                response.let {
                    albumList.setHasFixedSize(true)
albumList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,true)
        albumList.adapter = AlbumAdapter(albums = it.body()!!.take(20))
                    db.userDao().insertAll(it.body()!!.toTypedArray())
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "erreur réseau", Toast.LENGTH_SHORT)
                    .show()
                db.userDao().getAll().let {
                    albumList.setHasFixedSize(true)
                    albumList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,true)
                    albumList.adapter = AlbumAdapter(albums = it.take(20))
                }
            }
        })
    }
}
