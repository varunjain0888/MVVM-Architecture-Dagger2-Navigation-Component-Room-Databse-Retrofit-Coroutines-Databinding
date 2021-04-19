package com.skills.newsapp.data.model.mapper

import android.os.Parcel
import android.os.Parcelable

data class ArticleDataItem(
    var id: Long,
    var imageUrl: String?,
    var title: String?,
    var byline: String?,
    var abstractF: String?,
    var publishedDate: String?,
    var url: String?,
    var content: String?,
    var coverImageUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(imageUrl)
        parcel.writeString(title)
        parcel.writeString(byline)
        parcel.writeString(abstractF)
        parcel.writeString(publishedDate)
        parcel.writeString(url)
        parcel.writeString(content)
        parcel.writeString(coverImageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleDataItem> {
        override fun createFromParcel(parcel: Parcel): ArticleDataItem {
            return ArticleDataItem(parcel)
        }

        override fun newArray(size: Int): Array<ArticleDataItem?> {
            return arrayOfNulls(size)
        }
    }

}