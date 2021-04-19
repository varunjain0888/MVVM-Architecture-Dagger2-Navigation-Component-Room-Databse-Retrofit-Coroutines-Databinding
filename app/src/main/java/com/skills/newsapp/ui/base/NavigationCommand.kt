package com.skills.newsapp.ui.base

import androidx.navigation.NavDirections

/**
 *  Sealed class handling navigaiton in the app
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
    data class BackTo(val destinationId: Int) : NavigationCommand()
}