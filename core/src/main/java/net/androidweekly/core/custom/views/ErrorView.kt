package net.androidweekly.core.custom.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import net.androidweekly.core.R
import net.androidweekly.core.utils.android.bindView
import net.androidweekly.data.network.Resource

/**
 * Project: Android Weekly
 * Created: Nov 22, 2019
 *
 * @author Mohamed Hamdan
 */
class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var retryListener: (() -> Unit)? = null

    private val textViewMessage: TextView? by bindView(R.id.text_view_error_message)
    private val textViewTitle: TextView? by bindView(R.id.text_view_error_title)
    private val buttonRetry: Button? by bindView(R.id.button_view_error_retry)

    init {
        View.inflate(context, R.layout.view_error, this)
        visibility = View.GONE

        buttonRetry?.setOnClickListener {
            retryListener?.invoke()
            visibility = View.GONE
        }
    }

    fun setError(error: Resource.Error) {
        visibility = View.VISIBLE
        textViewMessage?.setText(error.error.resourceMessage)
        textViewTitle?.setText(error.error.resourceTitle)
    }

    fun setOnRetryClickListener(listener: (() -> Unit)?) {
        this.retryListener = listener
    }
}
