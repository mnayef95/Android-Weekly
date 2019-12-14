package net.androidweekly.advertise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

/**
 * Project: Android Weekly
 * Created: Dec 14, 2019
 *
 * @author Mohamed Hamdan
 */
class AdvertiseFragment : Fragment() {

    private var webView: WebView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_advertise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews()
        initWebView()
        loadAdvertiseUrl()
    }

    private fun findViews() {
        webView = view?.findViewById(R.id.web_view_fragment_advertise)
        progressBar = view?.findViewById(R.id.progress_bar_fragment_advertise)
    }

    private fun initWebView() {
        webView?.settings?.javaScriptEnabled = true

        webView?.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar?.visibility = View.GONE
            }
        }
    }

    private fun loadAdvertiseUrl() {
        webView?.loadUrl("https://androidweekly.net/jobs/new")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webView = null
    }
}
