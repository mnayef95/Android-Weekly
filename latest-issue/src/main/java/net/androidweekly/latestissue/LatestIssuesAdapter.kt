package net.androidweekly.latestissue

import android.view.ViewGroup
import net.androidweekly.core.adapter.BaseAdapter
import net.androidweekly.core.adapter.BaseBindingViewHolder
import net.androidweekly.data.models.issues.Issue
import net.androidweekly.data.models.issues.IssueTitle
import net.androidweekly.latestissue.databinding.RowIssueBinding
import net.androidweekly.latestissue.databinding.RowTitleBinding

/**
 * Project: Android Weekly
 * Created: Oct 21, 2019
 *
 * @author Mohamed Hamdan
 */
class LatestIssuesAdapter(
    private val viewModel: LatestIssueViewModel
) : BaseAdapter<BaseBindingViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return when (viewType) {
            TITLE_ITEM_VIEW_TYPE -> {
                val binding = RowTitleBinding.inflate(inflater!!, parent, false)
                TitleViewHolder(binding)
            }
            else -> {
                val binding = RowIssueBinding.inflate(inflater!!, parent, false)
                IssueViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (viewModel.getIssue(position) is IssueTitle) {
            TITLE_ITEM_VIEW_TYPE
        } else {
            ISSUE_ITEM_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int {
        return viewModel.getIssueCount()
    }

    private inner class IssueViewHolder(binding: RowIssueBinding) : BaseBindingViewHolder(binding) {

        override fun bind(position: Int) {
            bind<RowIssueBinding> {
                issue = viewModel.getIssue(position) as Issue
            }
        }
    }

    private inner class TitleViewHolder(binding: RowTitleBinding) : BaseBindingViewHolder(binding) {

        override fun bind(position: Int) {
            bind<RowTitleBinding> {
                issueTitle = viewModel.getIssue(position) as IssueTitle
            }
        }
    }

    private companion object {

        private const val TITLE_ITEM_VIEW_TYPE = 1
        private const val ISSUE_ITEM_VIEW_TYPE = 2
    }
}
