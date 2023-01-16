package com.example.ration.calculate

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.ration.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChoseProductFromDBDialogFragment : DialogFragment() {

    private val productVM by sharedViewModel<CalculateViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.choose_products)
                .setItems(productVM.listAllProductInString,
                    DialogInterface.OnClickListener { dialog, which ->
                        productVM.addProductToCurrentList(
                            productVM.listAllProduct.value?.get(
                                which
                            )?.name ?: ""
                        )
                        Toast.makeText(
                            context,
                            getString(R.string.added_product),
                            Toast.LENGTH_SHORT
                        ).show()
                    })
            builder.create()
        }
        return dialog ?: throw IllegalStateException("Activity cannot be null")
    }
}