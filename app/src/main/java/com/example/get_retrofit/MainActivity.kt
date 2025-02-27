package com.example.get_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // manually added in the gradle module app file
import com.example.get_retrofit.post.PostScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PostScreen()  // Call composable function
            }
        }
    }
}

// Composable function to display posts using existing ViewModel
@Composable
fun GetScreen(viewModel: GetViewModel = viewModel() ) {
    val posts by viewModel.posts

    if (posts.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
}

// Composable function for displaying a single post
@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "ID " + post.id.toString(),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text ="UserId " + post.userId.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Title "+post.title,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text ="Body" + post.body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
