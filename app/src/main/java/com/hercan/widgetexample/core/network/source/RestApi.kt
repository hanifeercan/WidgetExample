package com.hercan.widgetexample.core.network.source

import com.hercan.widgetexample.core.network.dto.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {
    @GET("Programming?type=twopart")
    suspend fun getJoke(): Response<JokeResponse>
}