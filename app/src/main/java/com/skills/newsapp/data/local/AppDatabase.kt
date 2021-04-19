package com.skills.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.skills.newsapp.data.local.dao.ArticleDao
import com.skills.newsapp.data.local.db.ArticleEntity
import com.skills.newsapp.data.local.db.DataConverters
/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Database(
    entities = [ArticleEntity::class],
    version = 1
)
@TypeConverters(DataConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}