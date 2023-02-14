package com.example.ration.ration.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.ration.R
import com.example.ration.delete.ChooseDialogArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RulesDialog : DialogFragment() {

    private val args by navArgs<RulesDialogArgs>()
    private val message by lazy { args.message }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let { actvt ->
            val builder = MaterialAlertDialogBuilder(actvt)
            builder.setPositiveButton(R.string.accessibly) { _, _ ->
            }
            builder.setMessage(String.format("%s", message))
            builder.create()
        }
        return dialog ?: throw IllegalStateException("Activity cannot be null")
    }
}