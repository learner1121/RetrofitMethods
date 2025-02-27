package com.example.get_retrofit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import retrofit2.Response

class GetViewModel : ViewModel() {
    private val _posts = mutableStateOf<List<Post>>(emptyList())
    val posts: State<List<Post>> = _posts

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<List<Post>> = RetrofitInstance.api.getPosts()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _posts.value = response.body() ?: emptyList()
                    } else {
                        Log.e("API_ERROR", "Error: ${response.code()}")
                    }
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Exception: ${e.message}")
                _isLoading.value = false
            }
        }
    }
}
