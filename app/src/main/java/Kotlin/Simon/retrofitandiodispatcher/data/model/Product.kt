package Kotlin.Simon.retrofitandiodispatcher.data.model

data class Product(
    val availabilityStatus: String,
    val brand: String,
    val category: String,
    val description: String,
    val dimensions: Dimensions,
    val discountPercentage: String,
    val id: String,
    val images: List<String>,
    val meta: Meta,
    val minimumOrderQuantity: String,
    val price: String,
    val rating: String,
    val returnPolicy: String,
    val reviews: List<Review>,
    val shippingInformation: String,
    val sku: String,
    val stock: String,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
    val weight: String
)