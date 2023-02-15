package com.example.ration.ration.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.ration.databinding.FragmentRationBinding
import com.example.ration.ration.Constants
import com.example.ration.ration.OnRationItemListener
import com.example.ration.ration.RationAdapter
import com.example.ration.ration.RationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RationFragment : Fragment() {

    private val rationViewModel by sharedViewModel<RationViewModel>()
    private lateinit var binding: FragmentRationBinding
    private var adapter: RationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rationViewModel.setProducts()
        binding = FragmentRationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAdapter(view)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rationRecycleView)
        rationViewModel.rationList.observe(viewLifecycleOwner) {
            adapter?.setData(it)
        }
        rationViewModel.productList.observe(viewLifecycleOwner) {
            rationViewModel.setRationLastList()
            adapter?.setData(rationViewModel.rationList.value ?: emptyList())
        }
        binding.makeNewDietButton.setOnClickListener {
            rationViewModel.createNewRation.value = true
            EnterDataOfHumanFragment().show(requireFragmentManager(), "EnterDataOfHumanFragment")
        }
        rationViewModel.createNewRation.observe(viewLifecycleOwner) {
            if (it == true)
                binding.rationRecycleView.visibility = View.INVISIBLE
            else binding.rationRecycleView.visibility = View.VISIBLE
        }
        rationViewModel.isVisible.observe(viewLifecycleOwner) {
            if (it != true)
                binding.rationRecycleView.visibility = View.INVISIBLE
            else binding.rationRecycleView.visibility = View.VISIBLE
        }
        rationViewModel.calloriesOnDay.value =
            context?.getSharedPreferences(Constants.NAME_SP, Context.MODE_PRIVATE)
                ?.getInt("lastCalories", 0)
        if (rationViewModel.calloriesOnDay.value == 0) {
            rationViewModel.createNewRation.value = true
            EnterDataOfHumanFragment().show(requireFragmentManager(), "EnterDataOfHumanFragment")
        }
        if (rationViewModel.rationList.value?.isEmpty() == true || rationViewModel.rationList.value == null)
            rationViewModel.setRationLastList()
        setRecyclerView()
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rationViewModel.saveRation()
    }

    private fun createAdapter(view: View) {
        if (adapter == null) {
            adapter = RationAdapter(object : OnRationItemListener {
                override fun onChangeProduct(
                    timeToEat: String,
                    category: String,
                    position: Int,
                    calories: Int
                ) {
                    val action =
                        RationFragmentDirections.actionRationFragmentToChangeProductDialogFragment(
                            timeToEat,
                            category,
                            position, calories
                        )
                    Navigation.findNavController(binding.root).navigate(action)
                }
            })
        }
    }

    private fun setRecyclerView() {
        binding.rationRecycleView.adapter = adapter
        binding.rationRecycleView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
    }
}