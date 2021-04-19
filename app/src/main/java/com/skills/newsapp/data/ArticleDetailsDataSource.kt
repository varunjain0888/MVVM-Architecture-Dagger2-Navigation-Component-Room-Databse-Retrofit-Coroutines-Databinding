package com.skills.newsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skills.newsapp.data.model.mapper.ArticleDataItem
import com.skills.newsapp.utils.Resource

/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
interface ArticleDetailsDataSource {
   suspend fun getArticleDetails(url: ArticleDataItem?): LiveData<Resource<ArticleDataItem>>
}