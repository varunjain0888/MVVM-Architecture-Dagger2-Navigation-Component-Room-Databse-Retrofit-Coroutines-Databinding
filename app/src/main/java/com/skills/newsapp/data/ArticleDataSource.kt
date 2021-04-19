package com.skills.newsapp.data

import androidx.lifecycle.LiveData
import com.skills.newsapp.data.local.db.ArticleEntity
import com.skills.newsapp.utils.Resource
/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
interface ArticleDataSource {
    suspend fun getArticles(period: Int,forceRefresh:Boolean): LiveData<Resource<List<ArticleEntity>>>
}