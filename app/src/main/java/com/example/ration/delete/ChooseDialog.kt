package com.example.ration.delete

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.ration.R
import com.example.ration.ration.Constants
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChooseDialog : DialogFragment() {

    private val deleteViewModel by sharedViewModel<DeleteViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let { activity ->
            val builder = MaterialAlertDialogBuilder(activity)
            builder.setPositiveButton(R.string.yes) { _, _ ->
                deleteViewModel.deleteProduct()
                Toast.makeText(
                    context,
                    getString(R.string.deleted_product),
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.setNegativeButton(R.string.no) { _, _ ->
                dialog?.dismiss()
            }
            builder.setMessage(
                String.format(
                    "%s %s?",
                    getString(R.string.delete_product_text),
                    deleteViewModel.deletingProductName.value
                )
            )
            builder.setTitle(R.string.delete)
            builder.create()
        }
        return dialog ?: throw IllegalStateException("Activity cannot be null")
    }
}