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
        binding.breakfastProductTitleTextView.text = ration.breakfast.product.name
        binding.breakfastProductSubtitleTextView.text =
            String.format("%sг", ration.breakfast.product.weight.toString())
        binding.breakfastDrinkTitleTextView.text = ration.breakfast.drink.name
        binding.breakfastDrinkSubtitleTextView.text =
            String.format("%sг", ration.breakfast.drink.weight.toString())
        binding.lunchHotterTitleTextView.text = ration.lunch.hotter.name
        binding.lunchHotterSubtitleTextView.text =
            String.format("%sг", ration.lunch.hotter.weight.toString())
        binding.lunchSecondTitleTextView.text = ration.lunch.second.name
        binding.lunchSecondSubtitleTextView.text =
            String.format("%sг", ration.lunch.second.weight.toString())
        binding.lunchSaladTitleTextView.text = ration.lunch.salad.name
        binding.lunchSaladSubtitleTextView.text = String.format("%sг", ration.lunch.salad.weight.toString())
        binding.lunchDrinkTitleTextView.text = ration.lunch.drink.name
        binding.lunchDrinkSubtitleTextView.text = String.format("%sг", ration.lunch.drink.weight.toString())
        binding.dinnerSecondTitleTextView.text = ration.dinner.second.name
        binding.dinnerSecondSubtitleTextView.text =
            String.format("%sг", ration.dinner.second.weight.toString())
        binding.dinnerSaladTitleTextView.text = ration.dinner.salad.name
        binding.dinnerSaladSubtitleTextView.text =
            String.format("%sг", ration.dinner.salad.weight.toString())
        binding.dinnerDrinkTitleTextView.text = ration.dinner.drink.name
        binding.dinnerDrinkSubtitleTextView.text =
            String.format("%sг", ration.dinner.drink.weight.toString())
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