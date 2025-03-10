package com.hercan.widgetexample.core.data.repo

import com.hercan.widgetexample.core.model.Joke
import com.hercan.widgetexample.core.network.dto.JokeResponse
import retrofit2.Response

typealias RestResponse = Response<JokeResponse>

fun RestResponse.toJokeList(): Joke {
    return body()!!.let { joke ->
        Joke(
            delivery = "- " + joke.delivery, setup = "- " + joke.setup
        )
    }
}