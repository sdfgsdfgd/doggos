package com.example.nine.ui.news

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nine.databinding.FragmentDogsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * News grid/recyclerview [Fragment] subclass as the default destination in the navigation.
 */
open class DogsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentDogsBinding

    private val viewModel: DogsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogsBinding.inflate(inflater, container, false)

        binding.dogsList.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.setPortraitOrientation(false)

        Log.d("XXX", "KAAN KAAN KAAN")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



//        Handler().postDelayed(Runnable {
//
//
//            binding.dogsList.adapter!!.notifyDataSetChanged()
//
//
//        }, 6000)
    }
}