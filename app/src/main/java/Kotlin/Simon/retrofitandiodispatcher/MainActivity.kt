package Kotlin.Simon.retrofitandiodispatcher

import Kotlin.Simon.retrofitandiodispatcher.Presentation.ProductViewModel
import Kotlin.Simon.retrofitandiodispatcher.data.ProductRepositoryImp
import Kotlin.Simon.retrofitandiodispatcher.data.model.Product
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import Kotlin.Simon.retrofitandiodispatcher.ui.theme.CoroutinesAndRetrofitTheme
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ProductViewModel> (
        factoryProducer = {
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProductViewModel(ProductRepositoryImp(RetrofitInstance.api))
                    as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutinesAndRetrofitTheme {

                Surface(modifier = Modifier
                    .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    ) {

                    // product list is observed from the ProductViewModel using collectAsState() to monitor changes in real-time
                    val productList = viewModel.products.collectAsState().value
                    val context = LocalContext.current
                    //launched effect handles errors
                    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                        viewModel.showErrorToastChannel.collectLatest { show ->
                            if(show){
                                Toast.makeText(
                                    context,"Error",Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    if ( productList.isEmpty() ) {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }else{
                       LazyColumn(modifier = Modifier
                           .fillMaxSize(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           contentPadding = PaddingValues(16.dp)
                       ) {
                           items(productList.size){ index->
                               Product1(productList[index])
                               Spacer(modifier = Modifier.height(16.dp))
                           }
                       }
                    }

                }

            }
        }
    }
}

@Composable
fun Product1(product: Product){

    //image loading using coil
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(product.thumbnail)
            .size(Size.ORIGINAL).build()
    ).state

    Column (modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .height(300.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primaryContainer)
    ){

        if(imageState is AsyncImagePainter.State.Error){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
                 contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        if(imageState is AsyncImagePainter.State.Success){
            Image(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
                painter = imageState.painter,//Shows the image when the image is fetched
                contentDescription = product.title,//takes the title anad sets as the content description
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "${product.title}: \n price: ${product.price}", modifier =Modifier.padding(16.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp)
        Spacer(modifier = Modifier.height(6.dp))

        Text(text = product.description, modifier =Modifier.padding(16.dp),
            fontSize = 15.sp)


    }

}





















