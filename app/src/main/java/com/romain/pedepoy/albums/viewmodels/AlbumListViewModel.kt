package com.romain.pedepoy.albums.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romain.pedepoy.albums.data.Album
import com.romain.pedepoy.albums.data.AlbumRepository

class AlbumListViewModel internal constructor(
    albumRepository: AlbumRepository
) : ViewModel() {

    val albums: LiveData<List<Album>> =  albumRepository.getAlbums()
}
