package com.example.ration.ration

import com.example.ration.ProductModel
import com.example.ration.data_base.ProductDataBase
import com.example.ration.data_base.RationDataBase
import com.example.ration.ration.models.DayRationForBDModel

class RationRepository(
    private val productDB: ProductDataBase,
    private val rationDB: RationDataBase
) {
    suspend fun getAllProducts(): List<ProductModel> {
        return productDB.productDAO.getAllProducts() ?: emptyList()
    }

    suspend fun getProductByName(name: String): ProductModel {
        return productDB.productDAO.getByName(name) ?: ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0)
    }

    suspend fun getRation(): List<DayRationForBDModel> {
        return rationDB.rationDAO.getAllRation() ?: emptyList()
    }

    suspend fun setRation(rationModel: DayRationForBDModel) {
        rationDB.rationDAO.deleteRation(rationModel.day)
        rationDB.rationDAO.addRationData(rationModel)

    }
}