package com.example.ration.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ration.ProductModel
import com.example.ration.R
import com.example.ration.databinding.FragmentCalculateBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CalculateFragment : Fragment() {

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!
    private val calculateVM: CalculateViewModel by sharedViewModel<CalculateViewModel>()
    private var adapter: ChoseProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.enterVM = calculateVM
        calculateVM.addDefaultProductsToDB(requireContext())
        calculateVM.getAllProducts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addProductButton.setOnClickListener {
            calculateVM.getAllProducts()
            val action =
                CalculateFragmentDirections.actionCalculateFragmentToChoseProductFromDBDialogFragment()
            navigate(it, action)
        }
        if (adapter == null) {
            adapter = ChoseProductAdapter(object : OnItemListener {
                override fun onChangeWeight(productModel: ProductModel, weight: Double) {
                    calculateVM.listChoosedProduct.value?.get(
                        calculateVM.listChoosedProduct.value?.indexOf(
                            productModel
                        ) ?: -1
                    )?.weight = weight ?: 0.0
                    calculateVM.calculatingCPFC()
                }

                override fun onClickDelete(productModel: ProductModel) {
                    calculateVM.removeProductFromCurrentList(productModel)
                    calculateVM.calculatingCPFC()
                }

            })
        }
        calculateVM.listChoosedProduct.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
            calculateVM.calculatingCPFC()
            adapter?.notifyDataSetChanged()
        }


        calculateVM.calories.observe(viewLifecycleOwner) {
            binding.calculateCaloriesTextView.text =
                String.format("%s: %.2f", getString(R.string.calories), it.toFloat())
        }
        calculateVM.fat.observe(viewLifecycleOwner) {
            binding.calculateFatTextView.text = String.format(
                "%s: %.2f",
                getString(R.string.fat),
                it.toFloat()
            )
        }
        calculateVM.carbohydrate.observe(viewLifecycleOwner) {
            binding.calculateCarbohydrateTextView.text = String.format(
                "%s: %.2f",
                getString(R.string.carbohydrate),
                it.toFloat()
            )
        }
        calculateVM.protein.observe(viewLifecycleOwner) {
            binding.calculateProteinTextView.text = String.format(
                "%s: %.2f",
                getString(R.string.protein),
                it.toFloat()
            )
        }
        binding.addedProductRecyclerView.adapter = adapter
        binding.addedProductRecyclerView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }

    private fun navigate(view: View, action: NavDirections) {
        Navigation.findNavController(view).navigate(action)
    }
}