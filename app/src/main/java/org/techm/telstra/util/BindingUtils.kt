package org.techm.telstra.util

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.techm.telstra.R

/**
 * @class{BindingAdapter} - to bind the image view from server
 */

@SuppressLint("CheckResult")
@BindingAdapter("imageUrl")
fun loadImageUrl(view: ImageView, url: String?) {

    val options = RequestOptions()
    options.placeholder(R.drawable.no_image_icon)
    options.error(R.drawable.no_image_icon)

    Glide.with(view)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(view)
}