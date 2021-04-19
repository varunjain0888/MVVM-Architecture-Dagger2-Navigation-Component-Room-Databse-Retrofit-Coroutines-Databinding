package com.skills.newsapp.data.model.api

import com.google.gson.annotations.SerializedName

data class ApiResult<T>(@SerializedName("num_results") val totalCount: Int,
                     @SerializedName("results") val items: List<T>)