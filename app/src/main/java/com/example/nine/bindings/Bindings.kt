package com.example.nine.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun getImagePicasso(view: ImageView, url: String?) {
    url?.let {
        if (!url.isNullOrBlank())
            Picasso.get().load(url).fit().centerInside().into(view)
    }
}