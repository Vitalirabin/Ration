package com.example.ration.calculate

import com.example.ration.ProductModel
import com.example.ration.data_base.ProductDAO
import com.example.ration.data_base.ProductDataBase

class CalculateRepository(private val productDB: ProductDataBase) {
    suspend fun getProductByName(name: String): ProductModel {
        return productDB.productDAO.getByName(name) ?: ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0)
    }

    suspend fun getAllProducts(): List<ProductModel> {
        return productDB.productDAO.getAllProducts() ?: listOf()
    }

    suspend fun addProductToDB(productModel: ProductModel) {
        productDB.productDAO.deleteProduct(productModel.name)
        productDB.productDAO.addData(productModel)
    }
}