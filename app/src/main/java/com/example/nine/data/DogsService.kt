package com.example.nine.data

import com.example.nine.data.models.DogsImgResponse
import com.example.nine.data.models.DogsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface DogsService {
    @GET("api/breeds/list/all")
    fun serviceGetDogs(): Single<DogsResponse?>?

    @GET("api/breed/{breed_name}/images/random")
    fun serviceGetDogsImageUrl(@Path(value = "breed_name") breedName: String): Single<DogsImgResponse?>?
}