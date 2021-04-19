package com.skills.newsapp.di.module.articlelist

import com.skills.newsapp.ui.main.articlelist.ArticlesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Module
abstract class ArticleListFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideArticleFragmentFactory(): ArticlesListFragment
}