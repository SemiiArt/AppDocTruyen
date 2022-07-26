package com.tuyenhoang.appdoctruyenjsoupv2

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CommonApp {
    @JvmStatic
    @BindingAdapter(value = ["loadImage"])
    fun loadImage(image:ImageView,linkImage:String?){
        if (linkImage == null){
            image.setBackgroundResource(R.drawable.img)
        }
        Glide.with(image.context)
            .load(linkImage)
            .error(R.drawable.img)
            .placeholder(R.drawable.img)
            .into(image)
    }
}