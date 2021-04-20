package com.skills.newsapp.ui.main.articlelist

import androidx.lifecycle.*
import com.skills.newsapp.data.ArticleDataSource
import com.skills.newsapp.data.local.db.ArticleEntity
import com.skills.newsapp.data.model.mapper.ArticleDataItem
import com.skills.newsapp.ui.base.BaseViewModel
import com.skills.newsapp.utils.EspressoIdlingResource
import com.skills.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  Viewmodel responsible for managing data
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class ArticleViewModel(
    private val articleDataSource: ArticleDataSource
) : BaseViewModel() {

    init {
        fetchArticles(7,false)
    }

    // FOR DATA
    private val articlesMediatorLiveData= MediatorLiveData<List<ArticleDataItem>>()
    private var articleSource: LiveData<Resource<List<ArticleEntity>>> = MutableLiveData()
    val articlesLiveDataBinder: LiveData<List<ArticleDataItem>>
        get() = articlesMediatorLiveData

    fun fetchArticles(period: Int,forceRefresh:Boolean) {
        viewModelScope.launch(Dispatchers.Main) {

            EspressoIdlingResource.increment() // This is to handle UI testing in case of Asyncronous call so we tell Expresso to wait here.

            isLoading.value = true

            articlesMediatorLiveData.removeSource(articleSource) // We make sure there is only one source of livedata (allowing us properly refresh)

            withContext(Dispatchers.IO){
                articleSource = articleDataSource.getArticles(period,forceRefresh)
            }

            articlesMediatorLiveData.addSource(articleSource) {
                mapArticlesDataItem(it.data)
                if (it.status == Resource.Status.SUCCESS)  isLoading.value = false
                if (it.status == Resource.Status.ERROR) showToast.value = it?.error?.localizedMessage
            }

            EspressoIdlingResource.decrement()

        }
    }

    private fun mapArticlesDataItem(articles: List<ArticleEntity>?) {
       var articles = articles?.map {
            ArticleDataItem(
                it.id,
                if (!it.media.isNullOrEmpty()) it.media?.get(0)?.mediaMetaData?.get(2)?.url else "",
                it.title,
                it.byline,
                it.abstractF,
                it.published_date,
                it.url,
                "",
                if (!it.media.isNullOrEmpty()) it.media?.get(0)?.mediaMetaData?.get(1)?.url else "",
            )
        }
        articlesMediatorLiveData.value = articles
    }

    fun onRefresh(){
        fetchArticles(7,true)
    }
}