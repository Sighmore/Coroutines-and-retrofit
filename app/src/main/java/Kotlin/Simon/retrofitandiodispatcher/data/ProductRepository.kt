package Kotlin.Simon.retrofitandiodispatcher.data

import Kotlin.Simon.retrofitandiodispatcher.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    //coroutine
    suspend fun getProductList(): Flow<Result<List<Product>>>//calls the get product page and initializes a flow producer that takes the content of the product
}