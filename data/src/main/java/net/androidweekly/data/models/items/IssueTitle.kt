package net.androidweekly.data.models.items

import net.androidweekly.data.R

/**
 * Project: Android Weekly
 * Created: Nov 22, 2019
 *
 * @author Mohamed Hamdan
 */
data class IssueTitle(

    val title: String? = null
) : Item {

    override val layoutResource: Int
        get() = R.layout.row_title
}
