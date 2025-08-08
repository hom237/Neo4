package com.example.neo4.server.service

import com.example.neo4.server.request.RequestBoard
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface BoardService {
    @POST("/board")
    suspend fun createBoard(
        @Body requestBody: RequestBoard
    ): Unit

    @GET("/board")
    suspend fun getBoardListBySuspend() : List<RequestBoard>

    @GET("/board/{id}")
    suspend fun getBoardById(
        @Path("id") id: Int
    ) : RequestBoard

    @PATCH("/board")
    suspend fun updateBoard(
        @Body requestBody: RequestBoard
    ) : Unit

    @DELETE("/board/{id}")
    suspend fun deleteBoard(
        @Path("id") id: Int
    ): Unit
}