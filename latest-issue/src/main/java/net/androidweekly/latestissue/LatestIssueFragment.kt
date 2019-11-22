package net.androidweekly.latestissue

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import net.androidweekly.core.fragments.BaseFragment
import net.androidweekly.core.utils.android.bindView
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

    private val recyclerView: RecyclerView? by bindView(R.id.recycler_view_fragment_latest_issue)
    private var adapter: LatestIssuesAdapter? = null

    override val layoutId: Int = R.layout.fragment_latest_issue

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(viewModel)

        adapter = LatestIssuesAdapter(viewModel)
        recyclerView?.adapter = adapter
    }
}
