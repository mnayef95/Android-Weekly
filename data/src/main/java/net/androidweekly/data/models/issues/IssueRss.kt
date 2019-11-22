package net.androidweekly.data.models.issues

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

/**
 * Project: Android Weekly
 * Created: Oct 22, 2019
 *
 * @author Mohamed Hamdan
 */
@Root(name = "rss", strict = false)
@Namespace(reference = "http://purl.org/rss/1.0/modules/content/", prefix = "content")
@Attribute(name = "version", required = false)
data class IssueRss(

    @field:Element(name = "channel")
    var channel: IssueWrapper? = null
)
