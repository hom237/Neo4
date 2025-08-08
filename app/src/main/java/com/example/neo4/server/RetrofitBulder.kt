package com.example.neo4.server

import com.example.neo4.server.service.BoardService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private var gson: Gson? = null
    private var retrofit: Retrofit? = null
    private var boardService: BoardService? = null

    fun getGson(): Gson {
        if (gson == null) {
            gson = GsonBuilder()
                .setLenient()
                .create()
        }
        return gson!!
    }

    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl("http://172.30.1.58:8080")
                .addConverterFactory(
                    GsonConverterFactory
                        .create(getGson())
                )
                .build()
        }
        return retrofit!!
    }

    fun getBoardService(): BoardService {
        if (boardService == null) {
            boardService = getRetrofit().create(BoardService::class.java)
        }
        return boardService!!
    }
}