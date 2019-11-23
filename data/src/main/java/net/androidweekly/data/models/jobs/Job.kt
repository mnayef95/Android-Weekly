package net.androidweekly.data.models.jobs

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.droidsonroids.jspoon.annotation.Selector

/**
 * Project: Android Weekly
 * Created: Oct 21, 2019
 *
 * @author Mohamed Hamdan
 */
@Entity(tableName = "jobs")
data class Job(

    @Selector("li .open-trigger strong")
    val title: String? = null,

    @Selector("li .open-trigger small")
    val location: String? = null,

    @Selector("li p.hidden")
    val description: String? = null,

    @PrimaryKey
    @Selector("li a")
    val url: String = ""
)
