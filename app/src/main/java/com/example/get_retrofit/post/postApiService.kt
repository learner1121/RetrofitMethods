package com.example.get_retrofit.post

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

interface ApiService {
    @POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>
}
