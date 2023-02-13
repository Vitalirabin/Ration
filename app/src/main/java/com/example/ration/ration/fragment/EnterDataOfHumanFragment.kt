package com.example.ration.ration.fragment


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ration.R
import com.example.ration.databinding.FragmentDataOfHealthBinding
import com.example.ration.ration.Constants
import com.example.ration.ration.RationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EnterDataOfHumanFragment : Fragment() {

    private val rationVM by sharedViewModel<RationViewModel>()
    private lateinit var binding: FragmentDataOfHealthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataOfHealthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.endEnterButton.setOnClickListener {
            if (onClickEndButton())
                Navigation.findNavController(view).popBackStack()
        }
        setSexRules()
        setWeightRules()
        setHumanActivityRules()
        seeRules(binding.root, binding.lowActivityRulesImageButton)
        seeRules(binding.root, binding.lightActivityRulesImageButton)
        seeRules(binding.root, binding.averageActivityRulesImageButton)
        seeRules(binding.root, binding.highActivityRulesImageButton)
        seeRules(binding.root, binding.veryHighActivityRulesImageButton)
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
            var message = ""
            when (Button) {
                binding.lowActivityRulesImageButton -> {
                    message = getString(R.string.activity_low_rules)
                }
                binding.lightActivityRulesImageButton -> {
                    message = getString(R.string.activity_light_rules)
                }
                binding.averageActivityRulesImageButton -> {
                    message = getString(R.string.activity_average_rules)
                }
                binding.highActivityRulesImageButton -> {
                    message = getString(R.string.activity_high_rules)
                }
                binding.veryHighActivityRulesImageButton -> {
                    message = getString(R.string.activity_very_high_rules)
                }
            }
            val action =
                EnterDataOfHumanFragmentDirections.actionEnterDataOfHumanFragmentToRulesDialog(
                    message
                )
            Navigation.findNavController(view).navigate(action)
        }
    }
}