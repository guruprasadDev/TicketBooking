package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.ticketbooking.R
import com.example.ticketbooking.databinding.MainViewBinding

class MainViewFragment : Fragment() {
    private lateinit var binding: MainViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewListener()
    }

    private fun initViewListener() {
        binding.apply {
            cover.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    cover to "cover",
                    movieTitle to "movieTittle",
                    desc to "desc",
                    menuButton to "menuBtn",
                    rating to "rating",
                    descriptionButton to "decBtn",
                    status to "status"
                )
                findNavController().navigate(
                    R.id.action_mainViewFragment_to_coverView,
                    null,
                    null,
                    extras
                )
            }

            descriptionButton.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    cover to "cover",
                    movieTitle to "movieTittle",
                    desc to "desc",
                    binding.menuButton to "menuBtn",
                    rating to "rating",
                    descriptionButton to "decBtn",
                    status to "status"
                )
                findNavController().navigate(
                    R.id.action_mainViewFragment_to_descriptionView,
                    null,
                    null,
                    extras
                )
            }
        }
    }
}
