package com.skills.newsapp.ui.base

/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}