package com.skills.newsapp.ui.main

import androidx.lifecycle.ViewModelProvider
import com.skills.newsapp.R
import com.skills.newsapp.BR
import com.skills.newsapp.ViewModelProviderFactory
import com.skills.newsapp.databinding.ActivityMainBinding
import com.skills.newsapp.ui.base.BaseActivity
import dagger.android.HasAndroidInjector
import javax.inject.Inject
/**
 *  Single Activity responsible for handling all fragments, this is the entry point in app
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>(),
    HasAndroidInjector {
    // Providing object of ViewModelProviderFactory using dagger2
    @Inject
    lateinit var factory: ViewModelProviderFactory

    // BR class is the class which is generated by the Data Binding for storing all the reactive values to handle.
    override val dataBindingVariable: Int
        get() = BR.viewModel

    // Assign main activity layout
    override val bindingLayoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

}