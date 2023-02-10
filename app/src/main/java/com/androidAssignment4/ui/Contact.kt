package com.androidAssignment4.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val imageURL: String,
    val name: String,
    val career: String,
    val eMail: String = "",
    val phone: String = "0",
    val address: String = "",
    val birth: String = "",
    var id: String = name[0] + ((0 until 50000).random()).toString() + name[name.length - 1]
) : Parcelable {
}
