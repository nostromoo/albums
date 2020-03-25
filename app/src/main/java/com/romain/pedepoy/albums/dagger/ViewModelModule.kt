package com.romain.pedepoy.albums.dagger

import androidx.lifecycle.ViewModel
import com.romain.pedepoy.albums.viewmodels.AlbumListViewModel
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindAlbumsViewModel(viewModel: AlbumListViewModel): ViewModel
}
