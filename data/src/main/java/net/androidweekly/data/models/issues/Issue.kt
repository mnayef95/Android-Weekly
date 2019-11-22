package net.androidweekly.data.models.issues

import net.androidweekly.data.models.items.IssueItem
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Root(name = "item", strict = false)
data class Issue(

    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "pubDate", required = false)
    var publishDate: String? = null
) {

    fun getItems(): List<IssueItem> {
        return listOf()
    }
}
