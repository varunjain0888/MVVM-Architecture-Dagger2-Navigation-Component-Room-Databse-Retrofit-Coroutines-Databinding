package com.skills.newsapp.di.module.articledetail

import com.skills.newsapp.ui.main.articledetails.ArticleDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Module
abstract class ArticleDetailsFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideArticleDetailsFragmentFactory(): ArticleDetailsFragment
}