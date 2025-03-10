package com.hercan.widgetexample.core.network.source

import com.hercan.widgetexample.core.network.dto.JokeResponse
import retrofit2.Response
import javax.inject.Inject

class RestDataSourceImpl @Inject constructor(private val restApi: RestApi) : RestDataSource {
    override suspend fun getJoke(): Response<JokeResponse> {
        return restApi.getJoke()
    }
}