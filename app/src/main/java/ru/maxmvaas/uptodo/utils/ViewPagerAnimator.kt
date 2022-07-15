package ru.maxmvaas.uptodo.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator

import androidx.viewpager2.widget.ViewPager2

class ViewPagerAnimator {
    private var animFactor = 0
    private val animator = ValueAnimator()
    private val animationDelay = 700

    fun animateViewPager(pager: ViewPager2, offset: Int) {
        animator.apply {
            if (!isRunning && !pager.isFakeDragging) {
                removeAllUpdateListeners()
                removeAllListeners()
                setIntValues(0, -offset)
                duration = animationDelay.toLong()
                repeatCount = 0
                addUpdateListener { animation ->
                    val value = animFactor * animation.animatedValue as Int
                    if (!pager.isFakeDragging) {
                        pager.beginFakeDrag()
                    }
                    pager.fakeDragBy(value.toFloat())
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        animFactor = 1
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        pager.endFakeDrag()
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                        animFactor = -1
                    }
                })
                start()
            }
        }
    }
}