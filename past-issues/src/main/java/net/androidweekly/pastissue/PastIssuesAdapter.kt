package net.androidweekly.pastissue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import net.androidweekly.core.adapter.BaseAdapter
import net.androidweekly.core.adapter.BaseBindingViewHolder
import net.androidweekly.pastissue.databinding.RowPastIssueBinding

/**
 * Created by Furkan on 2019-12-09
 */

class PastIssuesAdapter(private val viewModel: PastIssueViewModel) :
    BaseAdapter<BaseBindingViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return ViewHolder(
            RowPastIssueBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return viewModel.getItemsCount()
    }

    private inner class ViewHolder(binding: ViewDataBinding) :
        BaseBindingViewHolder(binding) {

        override fun bind(position: Int) {
            bind<RowPastIssueBinding> { item = viewModel.getItem(position) }
        }
    }
}
