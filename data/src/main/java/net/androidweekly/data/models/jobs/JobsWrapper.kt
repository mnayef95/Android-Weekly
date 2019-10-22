package net.androidweekly.data.models.jobs

import pl.droidsonroids.jspoon.annotation.Selector

/**
 * Project: Android Weekly
 * Created: Oct 21, 2019
 *
 * @author Mohamed Hamdan
 */
data class JobsWrapper(

    @Selector(".conference-list li")
    val jobs: List<Job>? = null
)
