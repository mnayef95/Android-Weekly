package net.androidweekly.latestissue

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import net.androidweekly.core.BaseViewModel
import net.androidweekly.data.models.items.IssueItem
import net.androidweekly.data.models.items.IssueTitle
import net.androidweekly.data.models.items.Item
import net.androidweekly.data.repositories.issues.IssuesRepository

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class LatestIssueViewModel(
    private val issuesRepository: IssuesRepository
) : BaseViewModel(), LifecycleObserver {

    private val items: MutableList<Item> = mutableListOf()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        items.addAll(
            listOf(
                IssueTitle(title = "Mohamed"),
                IssueItem(title = "Mohamed")
            )
        )
    }

    fun getItemsCount(): Int {
        return items.size
    }

    fun getItem(position: Int): Item {
        return items[position]
    }
}
