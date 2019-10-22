package net.androidweekly.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.androidweekly.core.R

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Suppress("unused")
abstract class BaseAdapter<V : BaseViewHolder> : RecyclerView.Adapter<V>() {

    private var onItemClickListener: ((view: View, position: Int) -> Unit)? = null

    private var animationEnabled: Boolean = true

    protected var context: Context? = null
    protected var inflater: LayoutInflater? = null
    private var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        context = recyclerView.context
        inflater = LayoutInflater.from(context)

        handleAnimations()
    }

    private fun handleAnimations() {
        if (animationEnabled) {
            val manager = recyclerView?.layoutManager
            if (manager is GridLayoutManager) {
                handleGridAnimations(manager)
            } else if (manager is LinearLayoutManager) {
                handleLinearAnimations(manager)
            }
            if (itemCount > 0) {
                recyclerView?.post { recyclerView?.scheduleLayoutAnimation() }
            }
        }
    }

    private fun handleGridAnimations(manager: GridLayoutManager?) {
        val animationSet = if (manager?.orientation == GridLayoutManager.VERTICAL) {
            getBottomAnimationSet()
        } else {
            getEndAnimationSet()
        }
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.grid_layout_animation)
        controller.animation = animationSet
        recyclerView?.layoutAnimation = controller
    }

    private fun handleLinearAnimations(manager: LinearLayoutManager?) {
        val animationSet = if (manager?.orientation == GridLayoutManager.VERTICAL) {
            getBottomAnimationSet()
        } else {
            getEndAnimationSet()
        }
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.linear_layout_animation)
        controller.animation = animationSet
        recyclerView?.layoutAnimation = controller
    }

    private fun getBottomAnimationSet(): AnimationSet {
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(getTranslateAnimation(0f, 0f, DEFAULT_DELTA, 0f))
        animationSet.addAnimation(getAlphaAnimation())

        return animationSet
    }

    private fun getEndAnimationSet(): AnimationSet {
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(getTranslateAnimation(DEFAULT_DELTA, 0f, 0f, 0f))
        animationSet.addAnimation(getAlphaAnimation())
        return animationSet
    }

    private fun getAlphaAnimation(): Animation {
        val alphaAnimation = AlphaAnimation(0f, 1f)
        alphaAnimation.interpolator = OvershootInterpolator()
        alphaAnimation.duration = DEFAULT_DURATION
        return alphaAnimation
    }

    private fun getTranslateAnimation(
        fromXDelta: Float,
        toXDelta: Float,
        fromYDelta: Float,
        toYDelta: Float
    ): Animation {
        val translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, fromXDelta,
            Animation.RELATIVE_TO_PARENT, toXDelta,
            Animation.RELATIVE_TO_PARENT, fromYDelta,
            Animation.RELATIVE_TO_PARENT, toYDelta
        )
        translateAnimation.interpolator = OvershootInterpolator()
        translateAnimation.duration = DEFAULT_DURATION
        return translateAnimation
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
        this.onItemClickListener = null
        this.inflater = null
        this.context = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return getViewHolder(parent, viewType)
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): V

    override fun onBindViewHolder(holder: V, position: Int) {
        if (onItemClickListener != null) {
            handleClickListener(holder)
        }

        bind(holder, position)
    }

    open fun bind(holder: V, position: Int) {
        holder.bind(position)
    }

    private fun handleClickListener(holder: V) {
        holder.itemView.setOnClickListener { view ->
            onItemClicked(view, holder)
        }
    }

    open fun onItemClicked(view: View, holder: V) {
        val clickedPosition = holder.adapterPosition
        if (clickedPosition != RecyclerView.NO_POSITION) {
            onItemClickListener?.invoke(view, clickedPosition)
        }
    }

    fun setOnItemClickListener(onItemClickListener: (view: View, position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    fun getOnItemClickListener(): ((view: View, position: Int) -> Unit)? {
        return onItemClickListener
    }

    fun disableAnimations() {
        animationEnabled = false
    }

    fun scheduleLayoutAnimation() {
        if (animationEnabled) {
            recyclerView?.scheduleLayoutAnimation()
        }
    }

    private companion object {

        private const val DEFAULT_DELTA = 0.5f
        private const val DEFAULT_DURATION = 600L
    }
}
