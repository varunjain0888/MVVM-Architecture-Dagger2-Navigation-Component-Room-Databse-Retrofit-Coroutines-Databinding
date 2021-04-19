package com.skills.newsapp.di.builder

import com.skills.newsapp.di.module.articledetail.ArticleDetailsFragmentProvider
import com.skills.newsapp.ui.main.MainActivity
import com.skills.newsapp.di.module.articlelist.ArticleListFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [ArticleListFragmentProvider::class,ArticleDetailsFragmentProvider::class])

    abstract fun bindMainActivity(): MainActivity
}