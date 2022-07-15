package ru.maxmvaas.uptodo.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

import ru.maxmvaas.uptodo.R
import ru.maxmvaas.uptodo.databinding.FragmentOnboardingBinding
import ru.maxmvaas.uptodo.presentation.onboarding.view_pager_adapter.ViewPagerAdapter
import ru.maxmvaas.uptodo.utils.ViewPagerAnimator

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.viewPager
        viewPager.isUserInputEnabled = false
        viewPager.adapter = ViewPagerAdapter()
        binding.dotsIndicator.attachTo(viewPager)

        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                val labelsArray = resources.getStringArray(R.array.onboarding)
                setupAnimations()
                when (position) {
                    0 -> {
                        binding.textViewTitle.animate()
                        binding.textViewDescription.animate()
                        setOnboardingText(labelsArray[0], labelsArray[1])
                    }
                    1 -> {
                        binding.textViewTitle.animate()
                        binding.textViewDescription.animate()
                        setOnboardingText(labelsArray[2], labelsArray[3])
                    }
                    2 -> {
                        binding.textViewTitle.animate()
                        binding.textViewDescription.animate()
                        setOnboardingText(labelsArray[4], labelsArray[5])
                    }
                }
            }
        })

        val viewPagerAnimator = ViewPagerAnimator()

        binding.apply {
            buttonNext.setOnClickListener {
                if (viewPager.currentItem != 3) {
                    viewPagerAnimator.animateViewPager(viewPager, 30)
                }
            }

            buttonBack.setOnClickListener {
                if (viewPager.currentItem != 0) {
                    viewPagerAnimator.animateViewPager(viewPager, -30)
                }
            }
        }
    }

    private fun setOnboardingText(title: String, description: String) {
        binding.textViewTitle.text = title
        binding.textViewDescription.text = description
    }

    private fun setupAnimations() {
        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 700
        fadeIn.fillAfter = true
        binding.textViewTitle.animation = fadeIn
        binding.textViewDescription.animation = fadeIn
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "[OnboardingFragment]"
    }
}