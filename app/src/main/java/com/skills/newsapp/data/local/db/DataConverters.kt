package com.skills.newsapp.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/**
 *  Converter helper for databse entity
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class DataConverters {

    @TypeConverter
    fun fromMediaListToString(media : List<MediaBean>) : String {
        return Gson().toJson(media)
    }

    @TypeConverter
    fun fromStringToMediaList(media: String) : List<MediaBean> {
        return Gson().fromJson(media, object : TypeToken<List<MediaBean>>(){}.type)
    }

    @TypeConverter
    fun fromMetadataListToString(media : List<MediaMetaDataBean>) : String {
        return Gson().toJson(media)
    }

    @TypeConverter
    fun fromStringToMetadata(media: String) : List<MediaMetaDataBean> {
        return Gson().fromJson(media, object : TypeToken<List<MediaMetaDataBean>>(){}.type)
    }
}