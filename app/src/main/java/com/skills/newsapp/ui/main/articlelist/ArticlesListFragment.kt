package com.skills.newsapp.ui.main.articlelist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.skills.newsapp.R
import com.skills.newsapp.BR
import com.skills.newsapp.ViewModelProviderFactory
import com.skills.newsapp.data.model.mapper.ArticleDataItem
import com.skills.newsapp.databinding.FragmentArticleListBinding
import com.skills.newsapp.ui.base.BaseFragment
import com.skills.newsapp.ui.base.NavigationCommand
import com.skills.newsapp.ui.main.MainActivity
import javax.inject.Inject

/**
 *  Fragment to show articles list
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class ArticlesListFragment : BaseFragment<ArticleViewModel , FragmentArticleListBinding>(),
    ArticleItemListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var mAdapter: ArticleListAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_article_list

    // Get viewmodel instance
    override val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(
                this,
                factory
        ).get(ArticleViewModel::class.java)
    }

    override fun onRetryClick() {
        viewModel.fetchArticles(7,true)
    }

    override fun onItemClick(item: ArticleDataItem) {
        navigate(
            NavigationCommand.To(
                    ArticlesListFragmentDirections.toArticleDetailsFragment(
                    item
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = ArticleListAdapter(arrayListOf(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) (activity as MainActivity).setSupportActionBar(
            getViewDataBinding().toolbar
        )
        setHasOptionsMenu(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().rvList.layoutManager = LinearLayoutManager(activity)
        getViewDataBinding().rvList.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().rvList.adapter = mAdapter
    }
}