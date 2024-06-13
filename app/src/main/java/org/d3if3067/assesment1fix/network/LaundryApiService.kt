package org.d3if3067.assesment1fix.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3067.assesment1fix.model.Laundry
import org.d3if3067.assesment1fix.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://togisamuel.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LaundryApiService {
    @GET("json.php")
    suspend fun getLaundry(
        @Query("auth") userId: String
    ): List<Laundry>

    @Multipart
    @POST("json.php")
    suspend fun postLaundry(
        @Part("auth") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("berat") berat: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("json.php")
    suspend fun deleteLaundry(
        @Query("auth") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object LaundryApi {
    val service: LaundryApiService by lazy {
        retrofit.create(LaundryApiService::class.java)
    }
    fun getLaundryUrl(image: String): String {
        return "$BASE_URL$image"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }