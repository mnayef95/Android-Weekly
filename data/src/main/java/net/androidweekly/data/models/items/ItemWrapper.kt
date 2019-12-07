package net.androidweekly.data.models.items

import pl.droidsonroids.jspoon.annotation.Selector

/**
 * Project: Android Weekly
 * Created: Dec 6, 2019
 *
 * @author Mohamed Hamdan
 */
data class ItemWrapper(

    @Selector("center")
    val center: List<IssueTitle>? = null
)
