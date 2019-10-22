package net.androidweekly.data.models.issues

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@JsonClass(generateAdapter = true)
data class Issue(

    @Json(name = "id")
    val id: Long,

    @Json(name = "title")
    val title: String?,

    @Json(name = "title")
    val url: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "image")
    val image: String?
) : IssueItem
