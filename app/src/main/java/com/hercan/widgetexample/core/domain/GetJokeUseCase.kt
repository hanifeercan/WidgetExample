package com.hercan.widgetexample.core.domain

import com.hercan.widgetexample.core.common.ResponseState
import com.hercan.widgetexample.core.data.repo.Repository
import com.hercan.widgetexample.core.model.Joke
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJokeUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): Flow<ResponseState<Joke>> {
        return repository.getJoke()
    }
}