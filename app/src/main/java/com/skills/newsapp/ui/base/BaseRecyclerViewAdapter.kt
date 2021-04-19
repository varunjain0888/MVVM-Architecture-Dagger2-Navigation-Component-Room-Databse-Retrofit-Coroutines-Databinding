package com.skills.newsapp.ui.base

import androidx.recyclerview.widget.RecyclerView

/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
abstract class BaseRecyclerViewAdapter<T>(
    val items: MutableList<T>,
    val itemListener: BaseItemListener<T>
) :
    RecyclerView.Adapter<BaseViewHolder>() {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(items: List<T>?) {
        items?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
    }

}