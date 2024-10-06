package Kotlin.Simon.retrofitandiodispatcher.data

import Kotlin.Simon.retrofitandiodispatcher.data.model.Products
import retrofit2.http.GET

interface Api {

    @GET("products")

    //Coroutine
    suspend fun getProductList(): Products

    companion object{
        const val BASE_URL ="https://dummyjson.com/"
    }
}