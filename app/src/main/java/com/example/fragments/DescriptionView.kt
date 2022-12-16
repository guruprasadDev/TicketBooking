package com.example.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.ticketbooking.R
import com.example.ticketbooking.databinding.DescriptionViewBinding

class DescriptionView : Fragment() {
    private lateinit var binding: DescriptionViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DescriptionViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementEnterTransition = animation
        initListener()
    }

    private fun initListener() {
        binding.apply {
            cover.setOnClickListener {
                val extra = FragmentNavigatorExtras(
                    cover to "cover",
                    movieTitle to "movieTitle",
                    desc to "desc",
                    menuButton to "menuBtn",
                    rating to "rating",
                )
                findNavController().navigate(
                    R.id.action_descriptionView_to_coverView,
                    null,
                    null,
                    extra
                )
            }
        }
    }
}