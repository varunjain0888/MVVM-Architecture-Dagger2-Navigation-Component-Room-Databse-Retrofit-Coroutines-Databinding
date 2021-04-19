package com.skills.newsapp.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skills.newsapp.data.local.db.ArticleEntity
import com.skills.newsapp.ui.base.BaseRecyclerViewAdapter
import com.skills.newsapp.utils.Resource

/**
 *  Custom Binders for databinding
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
object BindingUtils {
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("adapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: List<T>?) {
        items?.let {
            (recyclerView.adapter as? BaseRecyclerViewAdapter<T>)?.apply {
                clearItems()
                addItems(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("circularImage")
    fun setImageUrlCrop(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }
}