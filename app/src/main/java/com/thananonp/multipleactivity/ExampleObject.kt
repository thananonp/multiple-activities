package com.thananonp.multipleactivity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExampleObject(
    val name: String,
    val surname: String,
    val age: Int
) : Parcelable