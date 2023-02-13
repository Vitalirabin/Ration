package com.example.ration.delete

import com.example.ration.ProductModel
import com.example.ration.data_base.ProductDataBase

class DeleteRepository(private val db: ProductDataBase) {
    suspend fun deleteProduct(name: String) {
        db.productDAO.deleteProduct(name)
    }

    suspend fun getAllProduct():List<ProductModel> {
        return db.productDAO.getAllProducts() ?: emptyList<ProductModel>()
    }
}