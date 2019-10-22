package net.androidweekly.data.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface BindingAdapters {

    @BindingAdapter("imageUrl")
    fun ImageView.setImageUrl(url: String?)

    @BindingAdapter("circleImageUrl")
    fun ImageView.setCircleImageUrl(url: String?)

    @BindingAdapter("roundedImageUrl", "radius")
    fun ImageView.setRoundedImageUrl(url: String?, radius: Int)
}
