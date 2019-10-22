package net.androidweekly.core.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Suppress("UNUSED_PARAMETER")
abstract class BaseFragment : DaggerFragment() {

    protected abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    // This injection is a workaround for a known issue in Gradle build system https://github.com/google/dagger/issues/955
    @Inject
    fun setDummy(dummy: String) {
        // No impl
    }
}
