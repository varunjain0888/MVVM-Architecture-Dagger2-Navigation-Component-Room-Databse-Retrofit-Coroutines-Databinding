package com.skills.newsapp.ui.main.articledetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.skills.newsapp.R
import com.skills.newsapp.BR
import com.skills.newsapp.ViewModelProviderFactory
import com.skills.newsapp.data.model.mapper.ArticleDataItem
import com.skills.newsapp.databinding.FragmentArticleDetailsBinding
import com.skills.newsapp.ui.base.BaseFragment
import com.skills.newsapp.ui.main.MainActivity
import com.skills.newsapp.utils.AppConstants
import javax.inject.Inject

/**
 *  Fragemnt to show article details
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class ArticleDetailsFragment :
    BaseFragment<ArticleDetailsViewModel, FragmentArticleDetailsBinding>() {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var articleDataItem: ArticleDataItem? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_article_details

    override val viewModel: ArticleDetailsViewModel by lazy {
        ViewModelProvider(this, factory).get(ArticleDetailsViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            articleDataItem = arguments?.getParcelable(AppConstants.ARTICLE)
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getArticleDetails(articleDataItem)
    }

    private fun setUp() {
        setUpToolbar()
        setArticle()
    }

    private fun setArticle() {
        if (articleDataItem != null) {
            getViewDataBinding().article = articleDataItem
        }
    }

    private fun setUpToolbar() {
        if (activity != null) {
            (activity as MainActivity).setSupportActionBar(getViewDataBinding().toolbar)
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
    }
}