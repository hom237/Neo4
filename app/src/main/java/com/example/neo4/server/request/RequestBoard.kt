package com.example.neo4.server.request

data class RequestBoard(
    val id: Int,
    val title: String,
    val content: String,
    val name: String
)
