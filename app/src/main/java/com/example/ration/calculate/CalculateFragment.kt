package com.example.ration.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ration.calculate.CalculateViewModel
import com.example.ration.databinding.FragmentCalculateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculateFragment:Fragment() {

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!
    private val enterVM: CalculateViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.enterVM = enterVM
        return binding.root
    }


}