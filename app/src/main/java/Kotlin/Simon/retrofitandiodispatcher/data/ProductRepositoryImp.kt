package Kotlin.Simon.retrofitandiodispatcher.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException


class ProductRepositoryImp (
    private val api: Api
): ProductRepository{

    override suspend fun getProductList(): Flow<Result<List<Kotlin.Simon.retrofitandiodispatcher.data.model.Product>>> {
        //implementing the flow
        return flow {
            //try and catch error handling
            val productFromApi = try {
                //getProduct coroutine call made from another coroutine
                api.getProductList()
                //an error of IO
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading products"))
                return@flow
                //A Http Exception Error
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading products"))
                return@flow
                //A random exception
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading products"))
                return@flow
            }
            //The flow emitter that emits the products
            emit(Result.Success(productFromApi.products))
        }
    }
}