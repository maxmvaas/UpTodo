package ru.maxmvaas.uptodo.presentation.onboarding.view_pager_adapter

import ru.maxmvaas.uptodo.R

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import ru.maxmvaas.uptodo.databinding.ItemImageBinding

class ViewPagerAdapter :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private val images = intArrayOf(
        R.drawable.ic_onboarding_image_first,
        R.drawable.ic_onboarding_image_second,
        R.drawable.ic_onboarding_image_third,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewPagerViewHolder(private var binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imageViewImagesContainer.setImageResource(image)
        }
    }
}

