package net.androidweekly.data.models.items

import kotlinx.android.parcel.Parcelize
import net.androidweekly.data.R
import pl.droidsonroids.jspoon.annotation.Selector

/**
 * Project: Android Weekly
 * Created: Nov 22, 2019
 *
 * @author Mohamed Hamdan
 */
@Parcelize
data class IssueTitle(

    @Selector("div table h2")
    val title: String? = null
) : Item {

    override val layoutResource: Int
        get() = R.layout.row_title
}
