package net.androidweekly.data.models.issues

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Project: Android Weekly
 * Created: Oct 22, 2019
 *
 * @author Mohamed Hamdan
 */
@Root(name = "channel", strict = false)
data class IssueWrapper(

    @field:ElementList(inline = true)
    var items: List<Issue>? = null
)
