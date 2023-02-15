package com.example.ration.ration.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.ration.R
import com.example.ration.delete.ChooseDialogArgs
import com.example.ration.ration.RationViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RulesDialog : DialogFragment() {

    private val rationVM by sharedViewModel<RationViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let { actvt ->
            val builder = MaterialAlertDialogBuilder(actvt)
            builder.setPositiveButton(R.string.accessibly) { _, _ ->
            }
            builder.setMessage(String.format("%s", rationVM.message))
            builder.create()
        }
        return dialog ?: throw IllegalStateException("Activity cannot be null")
    }
}