package com.skills.newsapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
/**
 *  BaseFragment
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
abstract class BaseFragment<V : BaseViewModel , T : ViewDataBinding> : Fragment() {
    private lateinit var viewDataBinding: T

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.showToast.observe(
            this,
            { Toast.makeText(activity, it, Toast.LENGTH_LONG).show() })
    }

    fun getViewDataBinding(): T {
        return viewDataBinding
    }

    fun navigate(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> findNavController().navigate(command.directions)
            is NavigationCommand.Back -> findNavController().popBackStack()
            is NavigationCommand.BackTo -> findNavController().popBackStack(
                command.destinationId,
                false
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().setVariable(bindingVariable, viewModel)
        getViewDataBinding().lifecycleOwner = this
        getViewDataBinding().executePendingBindings()
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }
}