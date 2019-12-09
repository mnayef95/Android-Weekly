package net.androidweekly.pastissue

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import net.androidweekly.core.adapter.BaseAdapter
import net.androidweekly.core.adapter.BaseBindingViewHolder

/**
 * Created by Furkan on 2019-12-09
 */

class PastIssuesAdapter(private val viewModel: PastIssueViewModel) :
    BaseAdapter<BaseBindingViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return ViewHolder(DataBindingUtil.inflate(inflater!!, viewType, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_past_issue_item
    }

    override fun getItemCount(): Int {
        return viewModel.getItemsCount()
    }

    private inner class ViewHolder(private val binding: ViewDataBinding) :
        BaseBindingViewHolder(binding) {

        override fun bind(position: Int) {
            val item = viewModel.getItem(position)
            binding.setVariable(BR.item, item)
        }
    }
}
