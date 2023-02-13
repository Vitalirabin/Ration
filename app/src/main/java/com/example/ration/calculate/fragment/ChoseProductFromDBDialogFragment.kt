package com.example.ration.calculate.fragment

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.calculate.CalculateViewModel
import com.example.ration.calculate.ChoseProductAdapter
import com.example.ration.calculate.OnDialogItemClick
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ChoseProductFromDBDialogFragment : DialogFragment() {

    private val productVM by sharedViewModel<CalculateViewModel>()
    private var adapter: ChoseProductAdapter? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let { actvt ->
            val builder = AlertDialog.Builder(actvt)
            val mView = layoutInflater.inflate(R.layout.dialog_choose_product, null)
            val listView = mView.findViewById<RecyclerView>(R.id.list_choose_product_item)
            if (adapter == null) {
                adapter = ChoseProductAdapter(object : OnDialogItemClick {
                    override fun onClick(name: String) {
                        productVM.addProductToCurrentList(name)
                        Toast.makeText(
                            context,
                            getString(R.string.added_product),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
            listView.adapter = adapter
            listView.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter?.setData(productVM.listAllDialogProduct)
            val searchView = mView.findViewById<EditText>(R.id.search_edit_text)
            searchView.addTextChangedListener(filterTextWatcher)
            builder.setView(mView)
            builder.setTitle(R.string.choose_products)
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