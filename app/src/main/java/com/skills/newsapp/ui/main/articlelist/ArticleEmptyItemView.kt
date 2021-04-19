package com.skills.newsapp.ui.main.articlelist
/**
 *  Class will take function as an argument and get triggered from item_article_empty_view
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class ArticleEmptyItemView(private val onRetry: () -> Unit) {
    fun onRetryClick() = onRetry.invoke()
}