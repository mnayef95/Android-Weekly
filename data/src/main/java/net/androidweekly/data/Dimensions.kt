@file:JvmName("Dimensions")

package net.androidweekly.data

import android.content.Context
import android.util.TypedValue

/**
 * Project: Taleb
 * Created: July 08, 2019
 *
 * @author Mohamed Hamdan
 */
fun Int.dpToPx(context: Context?): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context?.resources?.displayMetrics)
}
