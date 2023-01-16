package com.example.ration.calculate

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ration.ProductModel
import com.example.ration.data_base.DatabaseHelper
import com.example.ration.data_base.DatabaseHelper.Companion.COLUMN_CALORIES
import com.example.ration.data_base.DatabaseHelper.Companion.COLUMN_CARBOHYDRATE
import com.example.ration.data_base.DatabaseHelper.Companion.COLUMN_FAT
import com.example.ration.data_base.DatabaseHelper.Companion.COLUMN_NAME
import com.example.ration.data_base.DatabaseHelper.Companion.COLUMN_PROTEIN
import kotlinx.coroutines.launch

class CalculateViewModel(private val repository: CalculateRepository) : ViewModel() {

    val calories = MutableLiveData<Double>()
    val protein = MutableLiveData<Double>()
    val fat = MutableLiveData<Double>()
    val carbohydrate = MutableLiveData<Double>()

    val listChoosedProduct = MutableLiveData<MutableList<ProductModel>>()
    val listAllProduct = MutableLiveData<MutableList<ProductModel>>()
    var listAllProductInString = listAllProduct.value?.map {
        String.format(
            "%s %skKal Белки:%sг Жири:%sг Углеводы:%sг",
            it.name,
            it.calories,
            it.protein,
            it.fat,
            it.carbohydrate
        )
    }
        ?.toTypedArray()

    fun calculatingCPFC() {
        calories.value = 0.0
        protein.value = 0.0
        fat.value = 0.0
        carbohydrate.value = 0.0
        listChoosedProduct.value?.forEach {
            calories.value = it.calories * it.weight / 100 + calories.value!!
            protein.value = it.protein * it.weight / 100 + protein.value!!
            fat.value = it.fat * it.weight / 100 + fat.value!!
            carbohydrate.value = it.carbohydrate / 100 * it.weight + carbohydrate.value!!
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            listAllProduct.value = repository.getAllProducts().toMutableList()
            listAllProductInString = listAllProduct.value?.map {
                String.format(
                    "%s\n%skKal, Жири:%sг, Углеводы:%sг, Белки:%sг",
                    it.name,
                    it.calories,
                    it.fat,
                    it.carbohydrate,
                    it.protein
                )
            }
                ?.toTypedArray()
        }
    }

    fun addProductToCurrentList(name: String) {
        if (listChoosedProduct.value == null) {
            listChoosedProduct.value = mutableListOf()
        }
        viewModelScope.launch {
            val list = listChoosedProduct.value
            list?.add(repository.getProductByName(name))
            listChoosedProduct.value = list ?: mutableListOf()
        }
    }

    fun removeProductFromCurrentList(productModel: ProductModel) {
        viewModelScope.launch {
            val list = listChoosedProduct.value
            list?.remove(productModel)
            listChoosedProduct.value = list ?: mutableListOf()
        }
    }

    fun addDefaultProductsToDB(context: Context) {
        val databaseHelper = DatabaseHelper(context);
        databaseHelper.create_db();
        val db = databaseHelper.open()
        val userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
        viewModelScope.launch {
            while (userCursor.moveToNext()) {
                val productModel =
                    ProductModel(
                        userCursor.getString(userCursor.getColumnIndex(COLUMN_NAME) ?: 0),
                        userCursor.getString(
                            userCursor.getColumnIndex(
                                COLUMN_CALORIES
                            ) ?: 0
                        ).toDouble(),
                        userCursor.getString(
                            userCursor.getColumnIndex(
                                COLUMN_PROTEIN
                            ) ?: 0
                        ).toDouble(),
                        userCursor.getString(
                            userCursor.getColumnIndex(
                                COLUMN_FAT
                            ) ?: 0
                        ).toDouble(),
                        userCursor.getString(
                            userCursor.getColumnIndex(
                                COLUMN_CARBOHYDRATE
                            ) ?: 0
                        ).toDouble(), 0.0
                    )
                repository.addProductToDB(productModel)
            }
            userCursor.close()
        }
    }
}