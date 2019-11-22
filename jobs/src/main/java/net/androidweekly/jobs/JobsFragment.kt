package net.androidweekly.jobs

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import net.androidweekly.core.custom.views.ErrorView
import net.androidweekly.core.fragments.BaseFragment
import net.androidweekly.core.utils.android.observe
import net.androidweekly.data.network.Resource
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 21, 2019
 *
 * @author Mohamed Hamdan
 */
class JobsFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: JobsViewModel

    @Inject
    lateinit var customTabsIntent: CustomTabsIntent

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var errorView: ErrorView? = null

    private var adapter: JobsAdapter? = null

    override val layoutId: Int = R.layout.fragment_jobs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initAdapter()
        initListeners()
        getJobs()
    }

    override fun onDestroyView() {
        errorView?.setOnRetryClickListener(null)
        errorView = null
        progressBar = null
        recyclerView?.adapter = null
        recyclerView = null
        adapter = null
        super.onDestroyView()
    }

    private fun initViews() {
        recyclerView = view?.findViewById(R.id.recycler_view_fragment_jobs)
        progressBar = view?.findViewById(R.id.progress_bar_fragment_jobs)
        errorView = view?.findViewById(R.id.error_view_fragment_jobs)
    }

    private fun initAdapter() {
        adapter = JobsAdapter(viewModel)
        recyclerView?.adapter = adapter
    }

    private fun initListeners() {
        adapter?.setOnItemClickListener { _, position ->
            val job = viewModel.getJob(position)
            customTabsIntent.launchUrl(context, Uri.parse(job.url))
        }

        errorView?.setOnRetryClickListener { getJobs() }
    }

    private fun getJobs() {
        viewModel.getJobs().observe(this, this::handleResource)
    }

    private fun handleResource(resource: Resource) {
        when (resource) {
            is Resource.Loading -> {
                handleLoadingResource(resource)
            }
            is Resource.Success<*> -> {
                handleSuccessResource()
            }
            is Resource.Error -> {
                handleErrorResource(resource)
            }
        }
    }

    private fun handleLoadingResource(resource: Resource.Loading) {
        progressBar?.visibility = if (resource.show) View.VISIBLE else View.GONE
    }

    private fun handleSuccessResource() {
        adapter?.notifyDataSetChanged()
        handleLoadingResource(Resource.Loading(show = false))
    }

    private fun handleErrorResource(resource: Resource.Error) {
        errorView?.setError(resource)
        handleLoadingResource(Resource.Loading(show = false))
    }
}
