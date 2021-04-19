package com.skills.newsapp.data.repository

import android.text.TextUtils
import androidx.lifecycle.LiveData
import com.skills.newsapp.data.ArticleDetailsDataSource
import com.skills.newsapp.data.local.AppDatabase
import com.skills.newsapp.data.model.mapper.ArticleDataItem
import com.skills.newsapp.data.remote.ApiService
import com.skills.newsapp.data.remote.NetworkBoundResource
import com.skills.newsapp.utils.Resource
import kotlinx.coroutines.Deferred
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Singleton
class ArticleDetailsRepository @Inject constructor(
    private val apiService: ApiService,
    private val mAppDatabase: AppDatabase
) : ArticleDetailsDataSource {
    override suspend fun getArticleDetails(article: ArticleDataItem?): LiveData<Resource<ArticleDataItem>> {

       return object : NetworkBoundResource<ArticleDataItem, ArticleDataItem>() {

            override fun processResponse(response: ArticleDataItem?): ArticleDataItem? = response

            override suspend fun saveCallResults(article: ArticleDataItem?){
                mAppDatabase.articleDao().updateArticleById(article?.id,article?.content)
            }

            override fun shouldFetch(data: ArticleDataItem?): Boolean = TextUtils.isEmpty(data?.content)

           override suspend fun loadFromDb(): ArticleDataItem = mAppDatabase.articleDao().getArticleById(article?.id)


            override fun createCallAsync(): Deferred<ArticleDataItem>?{
                return null
            }

           override fun createExternalCall(): ArticleDataItem? {
               val document = Jsoup.connect(article?.url).get()
               article?.apply {
                   title = document.title()
                   content = document.select("p").text()
               }
               return article
           }

           override fun isExternalCall(): Boolean =true

       }.build().asLiveData()
    }
}