package com.skills.newsapp.utils

import com.skills.newsapp.BuildConfig

/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
object AppConstants {
    const val DB_NAME = BuildConfig.APPLICATION_ID + ".db"
    const val ARTICLE = "article"
    const val EMPTY_ITEM = 0
    const val LIST_ITEM = 1
    const val CONNECT_TIMEOUT: Long = 3
    const val READ_TIMEOUT: Long = 3
    const val WRITE_TIMEOUT: Long = 3
}