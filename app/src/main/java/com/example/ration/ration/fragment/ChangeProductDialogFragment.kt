package com.example.ration.ration.fragment

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.calculate.ChoseProductAdapter
import com.example.ration.calculate.DialogProductModel
import com.example.ration.calculate.OnDialogItemClick
import com.example.ration.ration.RationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChangeProductDialogFragment : DialogFragment() {

    private val rationVM by sharedViewModel<RationViewModel>()
    private var adapter: ChoseProductAdapter? = null
    private val args by navArgs<ChangeProductDialogFragmentArgs>()
    private val category by lazy { args.category }
    private val timeToEat by lazy { args.timeToEat }
    private val position by lazy { args.position }
    private val calories by lazy { args.calories }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let { actvt ->
            val builder = AlertDialog.Builder(actvt)
            val mView = layoutInflater.inflate(R.layout.dialog_choose_product, null)
            val listView = mView.findViewById<RecyclerView>(R.id.list_choose_product_item)
            if (adapter == null) {
                adapter = ChoseProductAdapter(object : OnDialogItemClick {
                    override fun onClick(name: String) {
                        rationVM.changeRationElement(timeToEat, category, position, name, calories)
                        Toast.makeText(
                            context,
                            getString(R.string.product_changed),
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog?.cancel()
                    }
                })
            }
            listView.adapter = adapter
            listView.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter?.setData(rationVM.productList.value?.map {
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
            }?.sortedBy { it.title }?.toMutableList() ?: emptyList())
            val searchView = mView.findViewById<EditText>(R.id.search_edit_text)
            searchView.addTextChangedListener(filterTextWatcher)
            builder.setView(mView)
            builder.setTitle(R.string.choose_product)
            builder.create()
        }
        return dialog ?: throw IllegalStateException("Activity cannot be null")
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
}