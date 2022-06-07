package com.example.restaurant.extension

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.restaurant.R

@BindingAdapter("visible")
fun setVisibility(view: View, isVisible: Boolean?) {
    view.visibility = if (isVisible == true) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["imageUrl", "width", "height"], requireAll = false)
fun loadImageUrl(view: ImageView, url: String?,width:Int,height:Int) {
    if (url != null) {
        Glide.with(view.context)
            .load(url)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.overrideOf(width, height))
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(view)
    } else {
        Glide.with(view.context)
            .load(R.drawable.ic_error_24)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.overrideOf(width, height))
            .into(view)
    }
}