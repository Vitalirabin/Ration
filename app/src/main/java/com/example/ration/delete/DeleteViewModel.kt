package com.example.ration.delete

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ration.ProductModel
import kotlinx.coroutines.launch

class DeleteViewModel(private val deleteRepository: DeleteRepository) : ViewModel() {

    val allProductInDB = MutableLiveData<List<ProductModel>>()
    fun deleteProduct(name: String) {
        viewModelScope.launch {
            deleteRepository.deleteProduct(name)
            allProductInDB.value=deleteRepository.getAllProduct()
        }
    }

    fun getAllProduct() {
        viewModelScope.launch {
            allProductInDB.value = deleteRepository.getAllProduct()
        }
    }
}