package com.skills.newsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.skills.newsapp.data.ArticleDataSource
import com.skills.newsapp.data.ArticleDetailsDataSource
import com.skills.newsapp.ui.main.MainViewModel
import com.skills.newsapp.ui.main.articledetails.ArticleDetailsViewModel
import com.skills.newsapp.ui.main.articlelist.ArticleViewModel
import javax.inject.Inject
import javax.inject.Singleton
/**
 *  Viewmodel factory is responsible for keeping all instance of viewmodel
 *  (e.g if configuration changes occur)
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val articleDataSource: ArticleDataSource,
    private val articleDetailsDataSource: ArticleDetailsDataSource
) : NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel() as T
            }
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(articleDataSource) as T
            }
             modelClass.isAssignableFrom(ArticleDetailsViewModel::class.java) -> {
                 ArticleDetailsViewModel(articleDetailsDataSource) as T
             }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}