package com.example.get_retrofit.post

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import com.example.get_retrofit.post.Post



class PostViewModel:ViewModel() {

    val _response = mutableStateOf<Post?>(null)
    val response: State<Post?> = _response


    fun SendData(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = postRetrofitInstance.api.createPost(post)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        _response.value = response.body()
                        Log.d("POST_SUCCESS", "Response: ${response.body()}")
                    } else {
                        Log.e("POST_ERROR", "Error Code: ${response.code()}, Body: ${response.errorBody()?.string()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("POST_EXCEPTION", "Exception: ${e.message}")
            }
        }
    }




}