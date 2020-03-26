package com.romain.pedepoy.albums.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.romain.pedepoy.albums.Utils.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DataTest {

    private lateinit var db: AppDatabase
    private lateinit var albumDao: AlbumDao

    private val alb1 = album1.copy()
    private val alb2 = album2.copy()
    private val alb3 = album3.copy()
    private val alb4 = album4.copy()
    private val albWithoutId = albumWithoutId.copy()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        albumDao = db.albumDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testSize() {
        runBlocking {
            albumDao.insertAll(listOf(alb1, alb2, alb3, alb4))
        }
        val list = getValue(albumDao.getAll())
        Assert.assertThat(list.size, Matchers.equalTo(4))

    }

    @Test
    fun insertSameAlbum() {
        runBlocking {
            albumDao.insertAll(listOf(alb1, alb1, alb3, alb4))
        }

        val list = getValue(albumDao.getAll())
        Assert.assertThat(list.size, Matchers.equalTo(3))

    }

    @Test
    fun insertAlbumWithoutId() {
        runBlocking {
            albumDao.insertAll(listOf(albWithoutId, alb1, alb1, alb3, alb4))
        }

        val list = getValue(albumDao.getAll())
        Assert.assertThat(list.size, Matchers.equalTo(4))

    }
}