package com.androidAssignment4.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.addCircularImage(photo: String?) {
    Glide.with(this.context)
        .load(photo)
        .circleCrop()
        .into(this)
}