package com.example.ration.ration.fragment


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import com.example.ration.R
import com.example.ration.databinding.FragmentDataOfHealthBinding
import com.example.ration.ration.Constants
import com.example.ration.ration.RationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EnterDataOfHumanFragment : DialogFragment() {

    private val rationVM by sharedViewModel<RationViewModel>()
    private lateinit var binding: FragmentDataOfHealthBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let { actvt ->
            val builder = AlertDialog.Builder(actvt)
            binding = FragmentDataOfHealthBinding.inflate(layoutInflater)
            builder.setView(binding.root)
            builder.setTitle(R.string.enter_data_of_human)
            builder.create()
        }
        binding.endEnterButton.setOnClickListener {
            if (onClickEndButton())
                dialog?.dismiss()
        }
        setSexRules()
        setWeightRules()
        setHumanActivityRules()
        seeRules(binding.root, binding.lowActivityRulesImageButton)
        seeRules(binding.root, binding.lightActivityRulesImageButton)
        seeRules(binding.root, binding.averageActivityRulesImageButton)
        seeRules(binding.root, binding.highActivityRulesImageButton)
        seeRules(binding.root, binding.veryHighActivityRulesImageButton)
        return dialog ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        rationVM.isVisible.value = true
        rationVM.createNewRation.value = false
    }

    private fun onClickEndButton(): Boolean {
        if (binding.humanAgeTextView.text.toString() == "" ||
            binding.humanHeightTextView.text.toString() == "" ||
            binding.humanWeightTextView.text.toString() == "" ||
            rationVM.purpose.value == null ||
            rationVM.activity.value == null ||
            rationVM.male.value == null
        ) {
            Toast.makeText(context, R.string.add_product_error, Toast.LENGTH_SHORT).show()
            return false
        } else {
            rationVM.calculateCalories(
                requireContext(),
                binding.humanWeightTextView.text.toString().toInt(),
                binding.humanHeightTextView.text.toString().toInt(),
                binding.humanAgeTextView.text.toString().toInt()
            )
            rationVM.setRationList()
            return true
        }
    }

    private fun setWeightRules() {
        binding.weightLoseCheck.setOnClickListener {
            if (binding.weightLoseCheck.isChecked) {
                binding.weightRetentionCheck.isChecked = false
                binding.weightUpCheck.isChecked = false
                rationVM.purpose.value = Constants.WEIGHT_LOSE
            } else rationVM.purpose.value = null
        }
        binding.weightRetentionCheck.setOnClickListener {
            if (binding.weightRetentionCheck.isChecked) {
                binding.weightLoseCheck.isChecked = false
                binding.weightUpCheck.isChecked = false
                rationVM.purpose.value = Constants.WEIGHT_RETENTION
            } else rationVM.purpose.value = null
        }
        binding.weightUpCheck.setOnClickListener {
            if (binding.weightUpCheck.isChecked) {
                binding.weightLoseCheck.isChecked = false
                binding.weightRetentionCheck.isChecked = false
                rationVM.purpose.value = Constants.WEIGHT_UP
            } else rationVM.purpose.value = null
        }
    }

    private fun setSexRules() {
        binding.maleCheck.setOnClickListener {
            if (binding.maleCheck.isChecked) {
                binding.femaleCheck.isChecked = false
                rationVM.male.value = true
            } else rationVM.male.value = null
        }
        binding.femaleCheck.setOnClickListener {
            if (binding.femaleCheck.isChecked) {
                binding.maleCheck.isChecked = false
                rationVM.male.value = false
            } else rationVM.male.value = null
        }
    }

    private fun setHumanActivityRules() {
        binding.lowActivityCheck.setOnClickListener {
            if (binding.lowActivityCheck.isChecked) {
                binding.lightActivityCheck.isChecked = false
                binding.averageActivityCheck.isChecked = false
                binding.highActivityCheck.isChecked = false
                binding.veryHighActivityCheck.isChecked = false
                rationVM.activity.value = Constants.LOW_ACTIVITY
            } else rationVM.activity.value = null
        }
        binding.lightActivityCheck.setOnClickListener {
            if (binding.lightActivityCheck.isChecked) {
                binding.lowActivityCheck.isChecked = false
                binding.averageActivityCheck.isChecked = false
                binding.highActivityCheck.isChecked = false
                binding.veryHighActivityCheck.isChecked = false
                rationVM.activity.value = Constants.LIGHT_ACTIVITY
            } else rationVM.activity.value = null
        }
        binding.averageActivityCheck.setOnClickListener {
            if (binding.averageActivityCheck.isChecked) {
                binding.lightActivityCheck.isChecked = false
                binding.lowActivityCheck.isChecked = false
                binding.highActivityCheck.isChecked = false
                binding.veryHighActivityCheck.isChecked = false
                rationVM.activity.value = Constants.AVERAGE_ACTIVITY
            } else rationVM.activity.value = null
        }
        binding.highActivityCheck.setOnClickListener {
            if (binding.highActivityCheck.isChecked) {
                binding.lightActivityCheck.isChecked = false
                binding.averageActivityCheck.isChecked = false
                binding.lowActivityCheck.isChecked = false
                binding.veryHighActivityCheck.isChecked = false
                rationVM.activity.value = Constants.HIGH_ACTIVITY
            } else rationVM.activity.value = null
        }
        binding.veryHighActivityCheck.setOnClickListener {
            if (binding.veryHighActivityCheck.isChecked) {
                binding.lightActivityCheck.isChecked = false
                binding.averageActivityCheck.isChecked = false
                binding.highActivityCheck.isChecked = false
                binding.lightActivityCheck.isChecked = false
                rationVM.activity.value = Constants.VERY_HIGH_ACTIVITY
            } else rationVM.activity.value = null
        }
    }


    private fun seeRules(view: View, Button: ImageButton) {
        Button.setOnClickListener {
            when (Button) {
                binding.lowActivityRulesImageButton -> {
                    rationVM.message = getString(R.string.activity_low_rules)
                }
                binding.lightActivityRulesImageButton -> {
                    rationVM.message = getString(R.string.activity_light_rules)
                }
                binding.averageActivityRulesImageButton -> {
                    rationVM.message = getString(R.string.activity_average_rules)
                }
                binding.highActivityRulesImageButton -> {
                    rationVM.message = getString(R.string.activity_high_rules)
                }
                binding.veryHighActivityRulesImageButton -> {
                    rationVM.message = getString(R.string.activity_very_high_rules)
                }
            }
            RulesDialog().show(requireFragmentManager(), "rules")
        }
    }
}