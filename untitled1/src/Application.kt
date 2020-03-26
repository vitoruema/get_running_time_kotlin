package com.example

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*


data class Model(val train_type: String, val train_length: Int,
                 val segments: List<List<List<String>>>, val track_length: Map<String, Float>,
                 val pred_ini: Float)


data class RunningTime(val running_time: Map<String, Float>)

fun main() = runBlocking<Unit> {
    val client = HttpClient(Apache) {
        install(JsonFeature)
    }
    val runningTime: RunningTime = client.get {
        url("http://127.0.0.1:8000/running_time/")
        contentType(ContentType.Application.Json)
        body = Model(train_type = "TRAIN_TYPE_ID_BOre",
            train_length = 1230,
            segments = listOf(
                listOf(listOf("SEGMENT_ORIGIN_PELICAN"), listOf("PELICANN3T", "PELICANNNET", "fake")),
                listOf(listOf("SEGMENT_ORIGIN_PENNET-NWT"), listOf("PELICANNNWT"))
            ),
            track_length = mapOf("fake" to 69.0.toFloat(),
                "PELICANN3T" to 8.405.toFloat(),
                "PELICANNNET" to 8.405.toFloat(),
                "PELICANNNWT" to 9.214.toFloat()
            ),
            pred_ini = 20.0.toFloat()
        )
    }
    println(runningTime)
    client.close()
}