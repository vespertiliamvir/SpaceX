package com.example.spacex.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .fallback(android.R.drawable.stat_notify_error).into(this)
}