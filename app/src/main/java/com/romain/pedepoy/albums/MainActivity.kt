package com.romain.pedepoy.albums

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.romain.pedepoy.albums.data.AlbumRepository
import com.romain.pedepoy.albums.data.AppDatabase
import com.romain.pedepoy.albums.viewmodels.AlbumListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val viewModel : AlbumListViewModel by lazy {
        AlbumListViewModel(AlbumRepository.getInstance(AppDatabase.getInstance(this).albumDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = AlbumAdapter()
        albumList.adapter = adapter
        albumList.setHasFixedSize(true)
        albumList.layoutManager = LinearLayoutManager(this)

        subscribeToModel()
    }

    private fun subscribeToModel() {
        viewModel.albums.observe(this){ albums ->
            (albumList.adapter as AlbumAdapter).submitList(albums)
        }
    }
}
