package com.example.ticketbooking.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import com.example.ticketbooking.R
import com.example.ticketbooking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        addConstraintSetAnimation()
    }

    private fun addConstraintSetAnimation() {

        binding?.apply {
            cover
            menuButton
            status
            movieTitle
            movieInfo
            desc
            rating
            descriptionButton
            var isCoverView = false
            var isDescriptionView = false

            val initialConstraint = ConstraintSet()
            initialConstraint.clone(root)

            val coverConstraint = ConstraintSet()
            coverConstraint.clone(this@MainActivity, R.layout.cover_view)

            val descriptionConstraint = ConstraintSet()
            descriptionConstraint.clone(this@MainActivity, R.layout.description_view)

            val mapOfDays: Map<TextView, TextView> = mapOf(
                day1 to date1,
                day2 to date2,
                day3 to date3,
                day4 to date4,
                day5 to date5,
                day6 to date6,
                day7 to date7
            )

            val days: List<TextView> = listOf(day1, day2, day3, day4, day5, day6, day7)

            for (day in days) {
                day.setOnClickListener { selectDate(it as TextView, descriptionConstraint) }
            }

            for (day in mapOfDays) {
                day.value.setOnClickListener { selectDate(day.key, descriptionConstraint) }
            }

            cover.setOnClickListener {
                if (!isCoverView) {
                    TransitionManager.beginDelayedTransition(root)
                    coverConstraint.applyTo(root)

                    valueAnimator()

                    isCoverView = true
                    isDescriptionView = false
                }

            }

            menuButton.setOnClickListener {
                if (isCoverView) {
                    TransitionManager.beginDelayedTransition(root)
                    initialConstraint.applyTo(root)

                    valueAnimator()

                    isCoverView = false
                    isDescriptionView = false
                } else if (isDescriptionView) {
                    TransitionManager.beginDelayedTransition(root)
                    initialConstraint.applyTo(root)

                    isCoverView = false
                    isDescriptionView = false
                }

            }

            descriptionButton.setOnClickListener {
                if (!isDescriptionView) {
                    TransitionManager.beginDelayedTransition(root)
                    descriptionConstraint.applyTo(root)

                    if (isCoverView) {
                        valueAnimator()
                        isCoverView = false
                    }


                    isDescriptionView = true
                }
            }
        }
    }

    private fun selectDate(day: TextView, destinationConstraint: ConstraintSet) {
        binding?.dateSelector?.id?.let {
            destinationConstraint.connect(
                it,
                ConstraintSet.START,
                day.id,
                ConstraintSet.START
            )
        }
        binding?.dateSelector?.id?.let {
            destinationConstraint.connect(
                it,
                ConstraintSet.END,
                day.id,
                ConstraintSet.END
            )
        }
        TransitionManager.beginDelayedTransition(binding?.root)
        destinationConstraint.applyTo(binding?.root)
    }

    private fun valueAnimator() {
        val anim = ValueAnimator()
        anim.setIntValues(Color.BLACK, Color.WHITE)
        anim.setEvaluator(ArgbEvaluator())
        anim.addUpdateListener {
            binding?.apply {
                menuButton.setColorFilter(it.animatedValue as Int)
                status.setTextColor(it.animatedValue as Int)
                movieTitle.setTextColor(it.animatedValue as Int)
                desc.setTextColor(it.animatedValue as Int)
                rating.setTextColor(it.animatedValue as Int)
                descriptionButton.setColorFilter(it.animatedValue as Int)

                anim.duration = 300
                anim.start()
            }
        }
    }
}
