package com.example.ration.delete

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ration.R
import com.example.ration.calculate.DialogProductModel
import com.example.ration.calculate.OnDialogItemClick
import com.example.ration.databinding.DialogChooseProductBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DeleteProductFromDBFragment : Fragment(), OnDialogItemClick {

    private lateinit var binding: DialogChooseProductBinding
    private var adapter: DeleteProductAdapter? = null
    private val deleteViewModel by sharedViewModel<DeleteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogChooseProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (adapter == null) {
            adapter = DeleteProductAdapter(this)
        }
        binding.listChooseProductItem.adapter = adapter
        binding.listChooseProductItem.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        deleteViewModel.allProductInDB.observe(viewLifecycleOwner) { list ->
            adapter?.setData(list.map {
                DialogProductModel(
                    it.name,
                    String.format(
                        "%sкKal Белки:%sг Жири:%sг Углеводы:%sг",
                        it.calories,
                        it.protein,
                        it.fat,
                        it.carbohydrate
                    )
                )
            })
        }
        android.os.Handler().postDelayed({ binding.searchEditText.setText("") }, 200)
        deleteViewModel.getAllProduct()
        binding.searchEditText.addTextChangedListener(filterTextWatcher)
    }

    private val filterTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence, start: Int, before: Int,
            count: Int
        ) {
            adapter?.filter?.filter(s)
        }
    }

    override fun onClick(name: String) {
        deleteViewModel.setDeleteName(name)
        ChooseDialog().show(requireFragmentManager(), "chooseDialog")
    }
}