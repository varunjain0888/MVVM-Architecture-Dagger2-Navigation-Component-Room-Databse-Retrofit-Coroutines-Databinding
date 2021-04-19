package com.skills.newsapp.data.local.dao

import androidx.room.*
import com.skills.newsapp.data.local.db.ArticleEntity
import com.skills.newsapp.data.model.mapper.ArticleDataItem
/**
 *  DAO handling database queries
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: List<ArticleEntity>?)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<ArticleEntity>

    @Query("UPDATE articles SET content =:content WHERE id = :id")
    fun updateArticleById(id: Long?,content:String?)

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleById(id: Long?): ArticleDataItem
}