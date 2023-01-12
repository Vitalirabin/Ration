package com.example.ration.chooseProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ration.calculate.CalculateViewModel
import com.example.ration.databinding.FragmentCalculateBinding
import com.example.ration.databinding.FragmentChooseProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseProductFragment : Fragment() {

    private var _binding: FragmentChooseProductBinding? = null
    private val binding get() = _binding!!
    private val chooseVW: ChooseProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseProductBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = chooseVW
        return binding.root
    }


}