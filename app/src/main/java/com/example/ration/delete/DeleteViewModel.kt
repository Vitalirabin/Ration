package com.example.ration.delete

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ration.ProductModel
import kotlinx.coroutines.launch

class DeleteViewModel(private val deleteRepository: DeleteRepository) : ViewModel() {

    val allProductInDB = MutableLiveData<List<ProductModel>>()
    val deletingProductName = MutableLiveData<String>()

    fun setDeleteName(name: String) {
        deletingProductName.value = name
    }

    fun deleteProduct() {
        viewModelScope.launch {
            deleteRepository.deleteProduct(deletingProductName.value ?: "")
            allProductInDB.value = deleteRepository.getAllProduct()
        }
    }

    fun getAllProduct() {
        viewModelScope.launch {
            allProductInDB.value = deleteRepository.getAllProduct()
        }
    }
}