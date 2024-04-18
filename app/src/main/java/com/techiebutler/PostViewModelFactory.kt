package com.techiebutler

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideRegionRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_ADDRESS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClientBuilder())
        .build()
}

private fun provideOkHttpClientBuilder(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    httpClient.addInterceptor(logging)
    return httpClient.build()
}

class PostViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val postApi = provideRegionRetrofit().create(PostApi::class.java)
    private val postRemoteRepository = PostRemoteRepositoryImpl(postApi)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(postRemoteRepository) as T
    }
}