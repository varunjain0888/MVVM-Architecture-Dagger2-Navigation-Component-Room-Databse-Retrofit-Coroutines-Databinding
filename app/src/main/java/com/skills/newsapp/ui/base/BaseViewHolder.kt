package com.skills.newsapp.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
abstract class BaseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int)
}