package com.skills.newsapp.ui.main.articlelist

/**
 *  Class will take function as an argument and get triggered from list_item_article
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class ArticleItemView(private val onItemClick: () -> Unit) {
    fun onItemClick() = onItemClick.invoke()
}