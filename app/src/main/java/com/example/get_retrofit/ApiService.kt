package com.example.get_retrofit

import retrofit2.http.GET
import retrofit2.Response


data class Post(
    val userId: Int,
    val id:Int,
    val title:String,
    val body:String
)
interface ApiService {
    @GET("posts")
    suspend fun createPost(post: com.example.get_retrofit.post.Post): Response<Post>
    abstract fun getPosts(): Response<List<Post>>
}


