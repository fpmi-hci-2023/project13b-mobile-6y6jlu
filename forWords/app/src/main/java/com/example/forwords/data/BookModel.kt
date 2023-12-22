package com.example.forwords.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookModel(
    val title: String,
    val img: String,
    val description: String
) : Parcelable
