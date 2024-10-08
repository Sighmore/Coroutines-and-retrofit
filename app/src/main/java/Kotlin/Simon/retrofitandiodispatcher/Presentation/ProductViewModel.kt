package Kotlin.Simon.retrofitandiodispatcher.Presentation

import Kotlin.Simon.retrofitandiodispatcher.data.ProductRepository
import Kotlin.Simon.retrofitandiodispatcher.data.Result
import Kotlin.Simon.retrofitandiodispatcher.data.model.Product
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    //A viewModel that reads the emit from the model(Product repository)
    private val productRepository: ProductRepository
): ViewModel() {

    //ViewModel/ MutableState of flow emitted They are displayed in the UI
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products=_products.asStateFlow()

    //A toast to show error in the UI
    private val  _showErrorToastChannel= Channel<Boolean> ()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {

        //This is where the coroutines start to be called..on the viewModel Scope
        viewModelScope.launch {
            productRepository.getProductList().collectLatest { result->
                when(result){
                    is Result.Error ->{
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success ->{
                        result.data?.let {
                            products->
                            _products.update { products }
                        }
                    }
                }
            }
        }
    }

}