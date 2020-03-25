package com.romain.pedepoy.albums.dagger

import android.app.Application
import com.romain.pedepoy.albums.data.AlbumsRemoteDataSource
import com.romain.pedepoy.albums.data.AppDatabase
import com.romain.pedepoy.albums.service.AlbumApi
import com.romain.pedepoy.albums.utilities.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAlbumApi() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(AlbumApi::class.java)

    @Singleton
    @Provides
    fun provideAlbumsRemoteDataSource(albumApi: AlbumApi)
            = AlbumsRemoteDataSource(albumApi)

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideAlbumDao(db: AppDatabase) = db.albumDao()

}
