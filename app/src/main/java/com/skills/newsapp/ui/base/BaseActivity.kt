package com.skills.newsapp.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
/**
 *  BaseActivity
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
abstract class BaseActivity<V : BaseViewModel, T : ViewDataBinding> : AppCompatActivity(),
    HasAndroidInjector {
        @Inject
        lateinit var fragmentDispatchingAndroidInjector : DispatchingAndroidInjector<Any>

        abstract val dataBindingVariable : Int

        abstract val viewModel : V

        @get: LayoutRes
        abstract val bindingLayoutId : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        val viewDataBinding = DataBindingUtil.setContentView<T>(this, bindingLayoutId)
        viewDataBinding.setVariable(dataBindingVariable, viewModel)
        viewDataBinding.executePendingBindings()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }

}