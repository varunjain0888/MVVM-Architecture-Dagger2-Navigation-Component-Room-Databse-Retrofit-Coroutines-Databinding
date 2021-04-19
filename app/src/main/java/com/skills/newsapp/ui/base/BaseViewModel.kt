package com.skills.newsapp.ui.base

import androidx.lifecycle.ViewModel
import com.skills.newsapp.utils.SingleLiveEvent

/**  Adapter to handle list view and binds data to each view
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
abstract class BaseViewModel : ViewModel() {
    val isLoading : SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast : SingleLiveEvent<String> = SingleLiveEvent()
}