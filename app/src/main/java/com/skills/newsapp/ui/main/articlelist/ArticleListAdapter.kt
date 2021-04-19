package com.skills.newsapp.ui.main.articlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.skills.newsapp.data.model.mapper.ArticleDataItem
import com.skills.newsapp.databinding.ItemArticleEmptyViewBinding
import com.skills.newsapp.databinding.ListItemArticleBinding
import com.skills.newsapp.ui.base.BaseRecyclerViewAdapter
import com.skills.newsapp.ui.base.BaseViewHolder
import com.skills.newsapp.utils.AppConstants.EMPTY_ITEM
import com.skills.newsapp.utils.AppConstants.LIST_ITEM

/**
 *  Adapter to handle list view and binds data to each view
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class ArticleListAdapter(items: MutableList<ArticleDataItem>, listener: ArticleItemListener) :
    BaseRecyclerViewAdapter<ArticleDataItem>(items, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            LIST_ITEM -> {
                ArticleViewHolder(
                    ListItemArticleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    ItemArticleEmptyViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isNotEmpty()) LIST_ITEM else EMPTY_ITEM
    }

    inner class ArticleViewHolder(private val mBinding: ListItemArticleBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val articleDataItem = items[position]
            mBinding.itemList = articleDataItem
            mBinding.item = ArticleItemView { itemListener.onItemClick(articleDataItem) }
            mBinding.executePendingBindings()
        }
    }

    inner class EmptyViewHolder(private val mBinding: ItemArticleEmptyViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.item = ArticleEmptyItemView { itemListener.onRetryClick() }
            mBinding.executePendingBindings()
        }
    }
}