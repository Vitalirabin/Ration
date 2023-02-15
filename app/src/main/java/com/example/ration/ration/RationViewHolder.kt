package com.example.ration.ration

import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.databinding.ItemRationBinding
import com.example.ration.ration.models.DayRationModel

class RationViewHolder(private val binding: ItemRationBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        ration: DayRationModel, onRationItemListener: OnRationItemListener, position: Int
    ) {
        binding.itemNumberTextView.text =
            String.format("День %s", (ration.day).toString())
        ration.breakfast.product.name.apply {
            if (this != "") {
                binding.breakfastProductTitleTextView.text = this
                binding.breakfastProductSubtitleTextView.text =
                    String.format("%sг", ration.breakfast.product.weight.toString())
            }
        }
        ration.breakfast.drink.name.apply {
            if (this != "") {
                binding.breakfastDrinkTitleTextView.text = this
                binding.breakfastDrinkSubtitleTextView.text =
                    String.format("%sг", ration.breakfast.drink.weight.toString())
            }
        }
        ration.lunch.hotter.name.apply {
            if (this != "") {
                binding.lunchHotterTitleTextView.text = this
                binding.lunchHotterSubtitleTextView.text =
                    String.format("%sг", ration.lunch.hotter.weight.toString())
            }
        }
        ration.lunch.second.name.apply {
            if (this != "") {
                binding.lunchSecondTitleTextView.text = this
                binding.lunchSecondSubtitleTextView.text =
                    String.format("%sг", ration.lunch.second.weight.toString())
            }
        }
        ration.lunch.salad.name.apply {
            if (this != "") {
                binding.lunchSaladTitleTextView.text = this
                binding.lunchSaladSubtitleTextView.text =
                    String.format("%sг", ration.lunch.salad.weight.toString())
            }
        }
        ration.lunch.drink.name.apply {
            if (this != "") {
                binding.lunchDrinkTitleTextView.text = this
                binding.lunchDrinkSubtitleTextView.text =
                    String.format("%sг", ration.lunch.drink.weight.toString())
            }
        }

        ration.dinner.second.name.apply {
            if (this != "") {
                binding.dinnerSecondTitleTextView.text = this
                binding.dinnerSecondSubtitleTextView.text =
                    String.format("%sг", ration.dinner.second.weight.toString())
            }
        }
        ration.dinner.salad.name.apply {
            if (this != "") {
                binding.dinnerSaladTitleTextView.text = this
                binding.dinnerSaladSubtitleTextView.text =
                    String.format("%sг", ration.dinner.salad.weight.toString())
            }
        }
        ration.dinner.drink.name.apply {
            if (this != "") {
                binding.dinnerDrinkTitleTextView.text = this
                binding.dinnerDrinkSubtitleTextView.text =
                    String.format("%sг", ration.dinner.drink.weight.toString())
            }
        }
        binding.breakfastCaloriesTextView.text =
            String.format("- %s кKal", ration.breakfast.calories.toString())
        binding.lunchCaloriesTextView.text =
            String.format("- %s кKal", ration.lunch.calories.toString())
        binding.dinnerCaloriesTextView.text =
            String.format("- %s кKal", ration.dinner.calories.toString())

        binding.changeBreakfastProductButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.BREAKFAST,
                Constants.BREAKFAST,
                position,
                (ration.breakfast.product.calories / 100 * ration.breakfast.product.weight).toInt()
            )
        }
        binding.changeBreakfastDrinkButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.BREAKFAST,
                Constants.CATEGORY_DRINKS,
                position,
                (ration.breakfast.drink.calories / 100 * ration.breakfast.drink.weight).toInt()
            )
        }
        binding.changeLunchSecondButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.LUNCH,
                Constants.CATEGORY_SECOND,
                position,
                (ration.lunch.second.calories / 100 * ration.lunch.second.weight).toInt()
            )
        }
        binding.changeLunchHotterButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.LUNCH,
                Constants.CATEGORY_HOTTER,
                position,
                (ration.lunch.hotter.calories / 100 * ration.lunch.hotter.weight).toInt()
            )
        }
        binding.changeLunchDrinkButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.LUNCH,
                Constants.CATEGORY_DRINKS,
                position,
                (ration.lunch.drink.calories / 100 * ration.lunch.drink.weight).toInt()
            )
        }
        binding.changeLunchSaladButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.LUNCH,
                Constants.CATEGORY_SALADS,
                position,
                (ration.lunch.salad.calories / 100 * ration.lunch.salad.weight).toInt()
            )
        }

        binding.changeDinnerSecondButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.DINNER,
                Constants.CATEGORY_SECOND,
                position,
                (ration.dinner.second.calories / 100 * ration.dinner.second.weight).toInt()
            )
        }
        binding.changeDinnerDrinkButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.DINNER,
                Constants.CATEGORY_DRINKS,
                position,
                (ration.dinner.drink.calories / 100 * ration.dinner.drink.weight).toInt()
            )
        }
        binding.changeDinnerSaladButton.setOnClickListener {
            onRationItemListener.onChangeProduct(
                Constants.DINNER,
                Constants.CATEGORY_SALADS,
                position,
                (ration.dinner.salad.calories / 100 * ration.dinner.salad.weight).toInt()
            )
        }
    }
}