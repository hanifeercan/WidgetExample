package com.hercan.widgetexample.core.network.source

import com.hercan.widgetexample.core.network.dto.JokeResponse
import retrofit2.Response

interface RestDataSource {
    suspend fun getJoke(): Response<JokeResponse>
}