package net.androidweekly.data.binding

import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import net.androidweekly.data.dpToPx
import net.androidweekly.data.glide.GlideApp

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class BindingAdaptersImpl : BindingAdapters {

    override fun ImageView.setImageUrl(url: String?) {
        GlideApp.with(this).load(url).into(this)
    }

    override fun ImageView.setCircleImageUrl(url: String?) {
        GlideApp.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(this)
    }

    override fun ImageView.setRoundedImageUrl(url: String?, radius: Int) {
        val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(radius.dpToPx(context).toInt()))
        GlideApp.with(this).load(url).apply(requestOptions).into(this)
    }
}
