package com.hercan.widgetexample.core.data.repo

import com.hercan.widgetexample.core.common.ResponseState
import com.hercan.widgetexample.core.model.Joke
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getJoke(): Flow<ResponseState<Joke>>
}