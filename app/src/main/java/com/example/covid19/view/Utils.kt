package com.example.covid19.view

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.covid19.R

class Utils {
    companion object{
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(iv: ImageView, link: String?) {
            if (link == null || link.equals("")) {
                Glide.with(iv)
                    .load(R.drawable.ic_vietnam)
                    .into(iv)
            } else {
                Glide.with(iv)
                    .load(link)
                    .placeholder(R.drawable.ic_vietnam)
                    .error(R.drawable.ic_vietnam)
                    .into(iv)
            }
        }

        @JvmStatic
        @BindingAdapter("updateText")
        fun updateText(tv: TextView, value:String?){
            tv.text = value
        }

    }
}