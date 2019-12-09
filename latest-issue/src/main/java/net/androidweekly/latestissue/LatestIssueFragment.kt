package net.androidweekly.latestissue

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.transition.TransitionManager
import net.androidweekly.core.custom.views.ErrorView
import net.androidweekly.core.fragments.BaseFragment
import net.androidweekly.core.utils.android.observe
import net.androidweekly.data.models.items.IssueItem
import net.androidweekly.data.models.items.IssueTitle
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
    private var cardViewLocalIssuesMessage: CardView? = null
    private var constraintLayoutParent: ConstraintLayout? = null
    private var buttonLocalIssuesRetry: Button? = null
    private var adapter: LatestIssuesAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var isSwiped: Boolean = false

    override val layoutId: Int = R.layout.fragment_latest_issue

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
        if (arguments?.containsKey(ARG_ITEMS) == true) {
            viewModel.setItems(arguments?.getParcelableArray(ARG_ITEMS))
        } else {
            getIssues()
        }
    }

    private fun initViews() {
        recyclerView = view?.findViewById(R.id.recycler_view_fragment_latest_issue)
        errorView = view?.findViewById(R.id.error_view_fragment_latest_issues)
        progressBar = view?.findViewById(R.id.progress_bar_fragment_latest_issues)
        cardViewLocalIssuesMessage =
            view?.findViewById(R.id.card_view_fragment_latest_issues_local_issues_message)
        constraintLayoutParent =
            view?.findViewById(R.id.constraint_layout_fragment_latest_issues_parent)
        buttonLocalIssuesRetry =
            view?.findViewById(R.id.button_fragment_latest_issues_local_issues_retry)
        swipeRefreshLayout =
            view?.findViewById(R.id.swipeRefreshLayout)
    }

    private fun initListeners() {
        errorView?.setOnRetryClickListener { getIssues() }

        viewModel.issuesResourceLiveData.observe(this, this::handleResource)

        viewModel.remoteIssuesFailedLiveData.observe(this) {
            TransitionManager.endTransitions(constraintLayoutParent!!)
            cardViewLocalIssuesMessage?.visibility = View.VISIBLE
            initAdapter()
        }

        buttonLocalIssuesRetry?.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayoutParent!!)
            cardViewLocalIssuesMessage?.visibility = View.GONE
            getIssues()
            adapter?.notifyDataSetChanged()
        }

        swipeRefreshLayout?.setOnRefreshListener {
            getIssues()
            isSwiped = true
        }
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
        progressBar?.visibility = if (resource.show && !isSwiped) View.VISIBLE else View.GONE
        swipeRefreshLayout?.isRefreshing = resource.show && isSwiped
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
            } else if (item is IssueTitle) {
                val args = Bundle()
                args.putParcelableArray(ARG_ITEMS, viewModel.getClickedSectionItems(position))
                args.putString(ARG_TITLE, item.title)
                findNavController().navigate(R.id.fragment_section_details, args)
            }
        }
        recyclerView?.adapter = adapter
    }

    private fun handleErrorResource(resource: Resource.Error) {
        errorView?.setError(resource)
    }

    private fun getIssues() {
        viewModel.getIssues()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView?.adapter = null
        recyclerView = null
        errorView?.setOnRetryClickListener(null)
        errorView = null
        progressBar = null
        adapter = null
        constraintLayoutParent = null
        buttonLocalIssuesRetry = null
        cardViewLocalIssuesMessage = null
    }

    private companion object {

        private const val ARG_ITEMS = "items"
        private const val ARG_TITLE = "title"
    }
}