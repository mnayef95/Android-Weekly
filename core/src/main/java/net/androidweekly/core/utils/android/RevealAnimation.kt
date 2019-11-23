package net.androidweekly.core.utils.android

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import androidx.core.content.ContextCompat
import net.androidweekly.core.R

/**
 * Project: Android Weekly
 * Created: Nov 23, 2019
 *
 * @author Mohamed Hamdan
 */
class RevealAnimation(
    private val view: View?,
    intent: Intent,
    private val activity: Activity,
    private var endColor: Int = 0
) {

    private val startColor: Int by lazy {
        ContextCompat.getColor(activity, R.color.colorPrimaryDark)
    }

    private val revealX by lazy { intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0) }
    private val revealY by lazy { intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0) }

    init {
        if (endColor == 0) {
            endColor = ContextCompat.getColor(activity, android.R.color.white)
        }
        view?.setBackgroundColor(startColor)
        view?.visibility = View.INVISIBLE
        if (view?.viewTreeObserver?.isAlive == true) {
            view.viewTreeObserver.addOnGlobalLayoutListener(
                object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity()
                        view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                }
            )
        }
    }

    fun revealActivity() {
        changeBackground(startColor, endColor)
        val finalRadius = ((view?.width ?: 0).coerceAtLeast(view?.height ?: 0) * RADIUS).toFloat()
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            view,
            revealX,
            revealY,
            0f,
            finalRadius
        )
        circularReveal.duration = DURATION
        circularReveal.interpolator = AccelerateInterpolator()
        view?.visibility = View.VISIBLE
        circularReveal.start()
    }

    fun unRevealActivity() {
        changeBackground(endColor, startColor)
        val finalRadius = ((view?.width ?: 0).coerceAtLeast(view?.height ?: 0) * RADIUS).toFloat()
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            view,
            revealX,
            revealY,
            finalRadius,
            0f
        )
        circularReveal.duration = DURATION
        circularReveal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view?.visibility = View.INVISIBLE
                activity.finish()
                activity.overridePendingTransition(0, 0)
            }
        })
        circularReveal.start()
    }

    private fun changeBackground(from: Int, to: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), from, to)
        colorAnimation.duration = DURATION
        colorAnimation.addUpdateListener { view?.setBackgroundColor(it.animatedValue as Int) }
        colorAnimation.start()
    }

    companion object {

        private const val DURATION = 400L
        private const val RADIUS = 1.1

        const val EXTRA_CIRCULAR_REVEAL_X = "REVEAL_X"
        const val EXTRA_CIRCULAR_REVEAL_Y = "REVEAL_Y"
    }
}
