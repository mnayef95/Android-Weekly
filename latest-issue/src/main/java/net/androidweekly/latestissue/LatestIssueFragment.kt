package net.androidweekly.latestissue

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import net.androidweekly.core.custom.views.ErrorView
import net.androidweekly.core.fragments.BaseFragment
import net.androidweekly.core.utils.android.observe
import net.androidweekly.data.models.items.IssueItem
import net.androidweekly.data.network.Resource
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 21, 2019
 *
 * @author Mohamed Hamdan
 */
class LatestIssueFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: LatestIssueViewModel

    @Inject
    lateinit var customTabsIntent: CustomTabsIntent

    private var recyclerView: RecyclerView? = null
    private var errorView: ErrorView? = null
    private var progressBar: ProgressBar? = null
    private var adapter: LatestIssuesAdapter? = null

    override val layoutId: Int = R.layout.fragment_latest_issue

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)

        initViews()
        getIssues()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView?.adapter = null
        recyclerView = null
        errorView = null
        progressBar = null
        adapter = null
    }

    private fun initViews() {
        recyclerView = view?.findViewById(R.id.recycler_view_fragment_latest_issue)
        errorView = view?.findViewById(R.id.error_view_fragment_latest_issues)
        progressBar = view?.findViewById(R.id.progress_bar_fragment_latest_issues)
    }

    private fun getIssues() {
        viewModel.getIssues().observe(this, this::handleResource)
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
        initAdapter()
    }

    private fun initAdapter() {
        adapter = LatestIssuesAdapter(viewModel)
        adapter?.setOnItemClickListener { _, position ->
            val item = viewModel.getItem(position)
            if (item is IssueItem) {
                customTabsIntent.launchUrl(context, Uri.parse(item.link))
            }
        }
        recyclerView?.adapter = adapter
    }

    private fun handleErrorResource(resource: Resource.Error) {
        errorView?.setError(resource)
    }
}
