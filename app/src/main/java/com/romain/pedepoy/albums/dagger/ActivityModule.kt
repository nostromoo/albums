package com.romain.pedepoy.albums.dagger

import com.romain.pedepoy.albums.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMaintActivity(): MainActivity?
}
