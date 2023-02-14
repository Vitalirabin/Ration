package com.example.ration.calculate.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.ration.ProductModel
import com.example.ration.R
import com.example.ration.calculate.CalculateViewModel
import com.example.ration.data_base.DatabaseHelper.Companion.TABLE_BREAKFAST
import com.example.ration.data_base.DatabaseHelper.Companion.TABLE_DRINKS
import com.example.ration.data_base.DatabaseHelper.Companion.TABLE_HOTTER
import com.example.ration.data_base.DatabaseHelper.Companion.TABLE_SALADS
import com.example.ration.data_base.DatabaseHelper.Companion.TABLE_SECOND
import com.example.ration.databinding.FragmentAddProductToDbBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AddProductToDBFragment : Fragment() {

    private val productVM by sharedViewModel<CalculateViewModel>()
    private lateinit var binding: FragmentAddProductToDbBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProductToDbBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.breakfastButton.setOnClickListener {
            addProductToDB(TABLE_BREAKFAST)
        }
        binding.soupButton.setOnClickListener {
            addProductToDB(TABLE_HOTTER)
        }
        binding.drinksButton.setOnClickListener {
            addProductToDB(TABLE_DRINKS)
        }
        binding.secondCoursesButton.setOnClickListener {
            addProductToDB(TABLE_SECOND)
        }
        binding.saladButton.setOnClickListener {
            addProductToDB(TABLE_SALADS)
        }
    }

    private fun addProductToDB(table: String) {
        if (binding.nameEditTextView.text.toString() == "" ||
            binding.caloriesEditTextView.text.toString() == "" ||
            binding.proteinEditTextView.text.toString() == "" ||
            binding.fatEditTextView.text.toString() == "" ||
            binding.carbohydrateEditTextView.text.toString() == ""
        ) {
            Toast.makeText(context, R.string.add_product_error, Toast.LENGTH_SHORT).show()
        } else {
            productVM.addNewProductToDB(
                ProductModel(
                    binding.nameEditTextView.text.toString(),
                    binding.caloriesEditTextView.text.toString().toDouble(),
                    binding.proteinEditTextView.text.toString().toDouble(),
                    binding.fatEditTextView.text.toString().toDouble(),
                    binding.carbohydrateEditTextView.text.toString().toDouble(), table, 0
                )
            )
            Toast.makeText(context, R.string.added_product, Toast.LENGTH_SHORT).show()
        }
    }
}