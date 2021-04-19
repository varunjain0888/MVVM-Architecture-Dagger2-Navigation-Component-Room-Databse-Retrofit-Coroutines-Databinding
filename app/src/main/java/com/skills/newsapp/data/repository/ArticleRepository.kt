package com.skills.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.skills.newsapp.data.ArticleDataSource
import com.skills.newsapp.data.local.AppDatabase
import com.skills.newsapp.data.local.db.ArticleEntity
import com.skills.newsapp.data.model.api.ApiResult
import com.skills.newsapp.data.remote.ApiService
import com.skills.newsapp.data.remote.NetworkBoundResource
import com.skills.newsapp.utils.Resource
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  Article repository handling logic for fetching data either from Database or from network,
 *  Uses NetworkBoundResource for handling this logic
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Singleton
class ArticleRepository @Inject constructor(
    private val apiService: ApiService,
    private val mAppDatabase: AppDatabase
) : ArticleDataSource {
    override suspend fun getArticles(period: Int,forceRefresh:Boolean): LiveData<Resource<List<ArticleEntity>>> {
        return  object : NetworkBoundResource<List<ArticleEntity>, ApiResult<ArticleEntity>>() {

            override fun processResponse(response: ApiResult<ArticleEntity>?): List<ArticleEntity>?
                    = response?.items

            override suspend fun saveCallResults(items: List<ArticleEntity>?){
                mAppDatabase.articleDao().insert(items)
            }

            override fun shouldFetch(data: List<ArticleEntity>?): Boolean
                    = data == null || data.isEmpty() || forceRefresh

            override suspend fun loadFromDb(): List<ArticleEntity>
                    = mAppDatabase.articleDao().getAllArticles()


            override fun createCallAsync(): Deferred<ApiResult<ArticleEntity>>
                    = apiService.getArticles(period)

            override fun createExternalCall(): ApiResult<ArticleEntity>? {
                return null
            }

            override fun isExternalCall(): Boolean =false

        }.build().asLiveData()
    }
}