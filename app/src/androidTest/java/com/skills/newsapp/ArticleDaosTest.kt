package com.skills.newsapp

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.skills.newsapp.data.local.AppDatabase
import com.skills.newsapp.data.local.db.ArticleEntity
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaosTest {
    private var mDatabase: AppDatabase? = null
    @Before
    fun init() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun uninit() {
        mDatabase?.close()
    }

    @Test
    fun testLoadPopularArticles() {
        var articleEntities = mutableListOf<ArticleEntity>()

        articleEntities.add(ArticleEntity().apply {
            id = 10211
            title = "News Today"
            url = "testing"
            published_date = "17/04/2021"
        })
        runBlocking {
            mDatabase?.articleDao()?.insert(articleEntities)
            val articles = mDatabase?.articleDao()?.getAllArticles()
            assertNotNull(articles)
        }
    }
}