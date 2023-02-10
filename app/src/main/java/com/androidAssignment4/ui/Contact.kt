package com.androidAssignment4.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val imageURL: String,
    val name: String,
    val career: String,
    val eMail: String = "",
    val phone: String = "",
    val address: String = "",
    val birth: String = "",
    var selected: Boolean = false,
    val id: Long = 1
) : Parcelable
