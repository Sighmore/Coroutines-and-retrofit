# Retrofit and IO Dispatcher with MVVM Architecture

This project demonstrates the use of `Retrofit`, `Coil`, `Gson`, `OkHttp`, and the `MVVM` architecture pattern in a Kotlin Android application. It fetches and displays a list of products, using coroutines for asynchronous operations and Coil for image loading.

## Features
- **MVVM Architecture**: Implements Model-View-ViewModel (MVVM) to separate concerns and allow easier testing and scalability.
- **Retrofit**: Used for making API requests to fetch product data.
- **Coil**: For efficient image loading.
- **Gson**: To parse JSON data into Kotlin objects.
- **OkHttp**: For networking and handling HTTP requests.
- **Kotlin Coroutines**: For managing background tasks and asynchronous API calls.

## Libraries Used
- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for Android and Java.
- [OkHttp](https://square.github.io/okhttp/): An HTTP & HTTP/2 client for Android.
- [Coil](https://coil-kt.github.io/coil/): An image loading library for Android backed by Kotlin Coroutines.
- [Gson](https://github.com/google/gson): A library to convert Java/Kotlin objects into JSON and vice versa.
- [Jetpack Compose](https://developer.android.com/jetpack/compose): Used for building native UI in the app.

## Architecture
- **Model**: Represents the data layer. `ProductRepositoryImp` uses `Retrofit` to fetch product data from an API.
- **ViewModel**: `ProductViewModel` manages UI-related data and handles business logic, exposing product data to the UI via `LiveData`/`StateFlow`.
- **View**: The `MainActivity` observes the `ViewModel` and updates the UI accordingly.

## How it Works
1. **API Request**: The app uses `Retrofit` to request data from a product API, parsing the response using `Gson`.
2. **MVVM**: `ProductRepositoryImp` interacts with the `ViewModel`, and the `ViewModel` exposes the product data via a state (`StateFlow` or `LiveData`).
3. **UI**: The products are displayed using `LazyColumn`, and Coil loads the product images asynchronously.
4. **Error Handling**: If the API call fails, a toast message is displayed.

## Folder Structure
```bash
Kotlin.Simon.retrofitandiodispatcher/
│
├── data/
│   ├── ProductRepositoryImp.kt      # Repository implementation for fetching products
│   ├── model/                       # Model classes for Products
│
├── presentation/
│   ├── ProductViewModel.kt          # ViewModel that handles product logic
│
├── ui/
│   ├── MainActivity.kt              # Main activity that sets up the UI
│
└── ui/theme/
    ├── Theme.kt                     # Theme and styling for Jetpack Compose
```

## Installation and Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/Coroutines-and-retrofir.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle to download all dependencies.
4. Run the project on an Android emulator or physical device.
