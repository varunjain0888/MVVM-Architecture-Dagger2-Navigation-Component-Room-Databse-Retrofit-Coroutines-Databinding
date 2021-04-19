package com.skills.newsapp.data.remote

import com.skills.newsapp.data.local.db.ArticleEntity
import com.skills.newsapp.data.model.api.ApiResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 *   Interface for making API calls
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
interface ApiService {
    @GET(ApiEndPoint.ENDPOINT_ARTICLES)
    fun getArticles(
        @Path("period") period: Int
    ): Deferred<ApiResult<ArticleEntity>>

    @GET
    suspend fun getArticleDetails(@Url url: String?) : Deferred<String>
}