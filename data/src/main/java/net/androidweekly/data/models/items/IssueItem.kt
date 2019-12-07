package net.androidweekly.data.models.items

import kotlinx.android.parcel.Parcelize
import net.androidweekly.data.R

/**
 * Project: Android Weekly
 * Created: Nov 22, 2019
 *
 * @author Mohamed Hamdan
 */
@Parcelize
data class IssueItem(

    val title: String? = null,

    val description: String? = null,

    val image: String? = null,

    val link: String? = null,

    val host: String? = null,

    val isSponsored: Boolean = false
) : Item {

    override val layoutResource: Int
        get() = R.layout.row_item
}
