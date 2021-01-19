package com.example.nine.domain

import com.example.nine.data.DogsService
import com.example.nine.data.models.DogsImgResponse
import com.example.nine.data.models.DogsResponse
import io.reactivex.Single
import javax.inject.Inject

class DogsRepository @Inject constructor(private val newsService: DogsService) {
    fun getDogs(): Single<DogsResponse?>? = newsService.serviceGetDogs()
    fun getImageUrls(breedName: String): Single<DogsImgResponse?>? = newsService.serviceGetDogsImageUrl(breedName)
}