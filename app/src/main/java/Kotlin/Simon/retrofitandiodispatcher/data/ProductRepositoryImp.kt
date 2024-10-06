package Kotlin.Simon.retrofitandiodispatcher.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException


class ProductRepositoryImp (
    private val api: Api
): ProductRepository{

    override suspend fun getProductList(): Flow<Result<List<Kotlin.Simon.retrofitandiodispatcher.data.model.Product>>> {
        return flow {
            val productFromApi = try {
                api.getProductList()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading products"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading products"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading products"))
                return@flow
            }
            emit(Result.Success(productFromApi.products))
        }
    }
}