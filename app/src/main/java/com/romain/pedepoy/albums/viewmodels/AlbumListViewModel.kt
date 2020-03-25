package com.romain.pedepoy.albums.viewmodels

import androidx.lifecycle.ViewModel
import com.romain.pedepoy.albums.data.AlbumRepository
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(
    albumRepository: AlbumRepository
) : ViewModel() {

    val albums = albumRepository.albums
}
