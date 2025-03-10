package com.hercan.widgetexample.core.data.repo

import com.hercan.widgetexample.core.common.ResponseState
import com.hercan.widgetexample.core.model.Joke
import com.hercan.widgetexample.core.network.source.RestDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val restDataSource: RestDataSource) : Repository {
    override suspend fun getJoke(): Flow<ResponseState<Joke>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getJoke()
            emit(ResponseState.Success(response.toJokeList()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }
}