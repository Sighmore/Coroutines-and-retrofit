package Kotlin.Simon.retrofitandiodispatcher.data

import Kotlin.Simon.retrofitandiodispatcher.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductList(): Flow<Result<List<Product>>>
}