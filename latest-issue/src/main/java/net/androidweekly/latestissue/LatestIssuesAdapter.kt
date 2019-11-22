package net.androidweekly.latestissue

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import net.androidweekly.core.adapter.BaseAdapter
import net.androidweekly.core.adapter.BaseBindingViewHolder

/**
 * Project: Android Weekly
 * Created: Oct 21, 2019
 *
 * @author Mohamed Hamdan
 */
class LatestIssuesAdapter(private val viewModel: LatestIssueViewModel) : BaseAdapter<BaseBindingViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return ViewHolder(DataBindingUtil.inflate(inflater!!, viewType, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return viewModel.getItem(position).layoutResource
    }

    override fun getItemCount(): Int {
        return viewModel.getItemsCount()
    }

    private inner class ViewHolder(private val binding: ViewDataBinding) : BaseBindingViewHolder(binding) {

        override fun bind(position: Int) {
            val item = viewModel.getItem(position)
            binding.setVariable(BR.item, item)
        }
    }
}
