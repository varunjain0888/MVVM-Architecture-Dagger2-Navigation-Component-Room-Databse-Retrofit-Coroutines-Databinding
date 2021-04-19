package com.skills.newsapp.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @SerializedName("byline")
    var byline: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("type")
    var type: String?,
    @SerializedName("published_date")
    var published_date: String?,
    @SerializedName("source")
    var source: String,
    @SerializedName("abstract")
    var abstractF: String,
    @SerializedName("media")
    var media : List<MediaBean>?,
    var createdAt: Long,
    @SerializedName("content")
    var content : String
){
    constructor() : this(0,"","","","","","","", mutableListOf(),0,"")
}

data class MediaBean (
    @SerializedName("media-metadata")
    var mediaMetaData: List<MediaMetaDataBean>? = null)

data class MediaMetaDataBean (
    var url: String? = null
)