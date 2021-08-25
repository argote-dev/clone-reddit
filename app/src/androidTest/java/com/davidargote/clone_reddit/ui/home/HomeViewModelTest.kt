package com.davidargote.clone_reddit.ui.home

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.davidargote.clone_reddit.core.Constants
import com.davidargote.clone_reddit.repository.post_repository.PostsRepository
import com.davidargote.clone_reddit.data.local.ApplicationDatabase
import com.davidargote.clone_reddit.data.remote.Service
import com.google.gson.GsonBuilder
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest : TestCase() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var db: ApplicationDatabase

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, ApplicationDatabase::class.java)
            .allowMainThreadQueries().build()

        val service = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_REDDIT_SERVICE)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(Service::class.java)

        val repository = PostsRepository(service, db.postDao())

        viewModel = HomeViewModel(repository)

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testHomeViewModelFetchPostNotIsNull() {
        viewModel.fetchPosts()
        val response = viewModel.getPosts()
        assert(response.value != null)
    }

}