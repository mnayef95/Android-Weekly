package net.androidweekly.core.adapter

import androidx.databinding.ViewDataBinding

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
abstract class BaseBindingViewHolder(viewDataBinding: ViewDataBinding) : BaseViewHolder(viewDataBinding.root) {

    var viewDataBinding: ViewDataBinding? = viewDataBinding

    @Suppress("UNCHECKED_CAST")
    inline fun <T : ViewDataBinding> bind(binding: T.() -> Unit) {
        binding(viewDataBinding as T)
        viewDataBinding?.executePendingBindings()
    }
}
