package net.androidweekly.latestissue

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import net.androidweekly.core.BaseViewModel
import net.androidweekly.data.models.issues.IssueItem

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class LatestIssueViewModel : BaseViewModel(), LifecycleObserver {

    private val list: MutableList<IssueItem> = mutableListOf()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        // No impl
    }

    fun getIssueCount(): Int {
        return list.size
    }

    fun getIssue(position: Int): IssueItem? {
        return list[position]
    }
}
