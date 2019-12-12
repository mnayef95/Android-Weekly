package net.androidweekly.pastissue


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.transition.TransitionManager
import net.androidweekly.core.custom.views.ErrorView
import net.androidweekly.core.fragments.BaseFragment
import net.androidweekly.core.utils.android.observe
import net.androidweekly.data.network.Resource
import javax.inject.Inject

class PastIssueFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: PastIssueViewModel

    override val layoutId: Int
        get() = R.layout.fragment_past_issue

    private var recyclerView: RecyclerView? = null
    private var errorView: ErrorView? = null
    private var progressBar: ProgressBar? = null
    private var cardViewLocalIssuesMessage: CardView? = null
    private var constraintLayoutParent: ConstraintLayout? = null
    private var buttonLocalIssuesRetry: Button? = null
    private var adapter: PastIssuesAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var isSwiped: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
        getPastIssues()
    }

    private fun initViews() {
        recyclerView = view?.findViewById(R.id.recycler_view_fragment_past_issue)
        errorView = view?.findViewById(R.id.error_view_fragment_past_issues)
        progressBar = view?.findViewById(R.id.progress_bar_fragment_past_issues)
        cardViewLocalIssuesMessage =
            view?.findViewById(R.id.card_view_fragment_past_issues_local_issues_message)
        constraintLayoutParent =
            view?.findViewById(R.id.constraint_layout_fragment_past_issues_parent)
        buttonLocalIssuesRetry =
            view?.findViewById(R.id.button_fragment_past_issues_local_issues_retry)
        swipeRefreshLayout =
            view?.findViewById(R.id.swipeRefreshLayout)
    }

    private fun initListeners() {
        errorView?.setOnRetryClickListener { getPastIssues() }

        viewModel.issuesResourceLiveData.observe(this, this::handleResource)

        viewModel.remoteIssuesFailedLiveData.observe(this) {
            TransitionManager.endTransitions(constraintLayoutParent!!)
            cardViewLocalIssuesMessage?.visibility = View.VISIBLE
            initAdapter()
        }

        buttonLocalIssuesRetry?.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayoutParent!!)
            cardViewLocalIssuesMessage?.visibility = View.GONE
            getPastIssues()
            adapter?.notifyDataSetChanged()
        }

        swipeRefreshLayout?.setOnRefreshListener {
            getPastIssues()
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
        adapter = PastIssuesAdapter(viewModel)
        adapter?.setOnItemClickListener { _, position ->

            val item = viewModel.getItem(position)
            val args = Bundle()

            args.putParcelableArray(ARG_ITEMS, item?.getItems()?.toTypedArray())
            args.putString(ARG_TITLE, item?.title)
            findNavController().navigate(R.id.item_menu_activity_main_latest_issue, args)
        }
        recyclerView?.adapter = adapter
    }

    private fun handleErrorResource(resource: Resource.Error) {
        errorView?.setError(resource)
    }

    private fun getPastIssues() {
        viewModel.getPastIssues()
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
        swipeRefreshLayout = null
    }

    private companion object {

        private const val ARG_ITEMS = "items"
        private const val ARG_TITLE = "title"
    }
}
