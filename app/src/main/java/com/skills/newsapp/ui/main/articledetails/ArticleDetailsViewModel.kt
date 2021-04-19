package com.skills.newsapp.ui.main.articledetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skills.newsapp.data.ArticleDetailsDataSource
import com.skills.newsapp.data.local.db.ArticleEntity
import com.skills.newsapp.data.model.mapper.ArticleDataItem
import com.skills.newsapp.ui.base.BaseViewModel
import com.skills.newsapp.utils.EspressoIdlingResource
import com.skills.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  Viewmodel which will be responsible for handling detail data
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class ArticleDetailsViewModel(
    private val articleDetailsDataSource: ArticleDetailsDataSource
) : BaseViewModel() {
    private val articlesMediatorLiveData= MediatorLiveData<ArticleDataItem>()
    private var articleSource: LiveData<Resource<ArticleDataItem>> = MutableLiveData()
    val articlesDetailsLiveData: LiveData<ArticleDataItem>
        get() = articlesMediatorLiveData
    fun getArticleDetails(article: ArticleDataItem?){
        viewModelScope.launch(Dispatchers.Main) {

            EspressoIdlingResource.increment()

            isLoading.value = true

            articlesMediatorLiveData.removeSource(articleSource) // We make sure there is only one source of livedata (allowing us properly refresh)

            withContext(Dispatchers.IO){
                articleSource = articleDetailsDataSource.getArticleDetails(article)
            }

            articlesMediatorLiveData.addSource(articleSource) {
                articlesMediatorLiveData.value = it.data
                if (it.status == Resource.Status.ERROR) showToast.value = it?.error?.localizedMessage
            }

            isLoading.value = false

            EspressoIdlingResource.decrement()
        }
    }
}