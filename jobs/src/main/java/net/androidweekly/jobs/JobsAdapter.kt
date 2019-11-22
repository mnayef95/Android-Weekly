package net.androidweekly.jobs

import android.view.ViewGroup
import net.androidweekly.core.adapter.BaseAdapter
import net.androidweekly.core.adapter.BaseBindingViewHolder
import net.androidweekly.jobs.databinding.RowJobBinding

/**
 * Project: Android Weekly
 * Created: Oct 21, 2019
 *
 * @author Mohamed Hamdan
 */
class JobsAdapter(private val viewModel: JobsViewModel) : BaseAdapter<BaseBindingViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return ViewHolder(RowJobBinding.inflate(inflater!!, parent, false))
    }

    override fun getItemCount(): Int {
        return viewModel.getJobsCount()
    }

    private inner class ViewHolder(binding: RowJobBinding) : BaseBindingViewHolder(binding) {

        override fun bind(position: Int) {
            bind<RowJobBinding> { job = viewModel.getJob(position) }
        }
    }
}
