package com.example.get_retrofit.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable

fun PostScreen(viewModel: PostViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column (modifier = Modifier.fillMaxSize()
        .padding(16.dp),
        Arrangement.Center){

        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = { Text("Title")},
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
          OutlinedTextField(
            value = body,
            onValueChange = {body = it},
            label = { Text("Body")},
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val newPost  = Post(userId = 1,id = 5, title = title, body = body)
                viewModel.SendData(newPost)

            },
            shape = RectangleShape
        ) {
            Text(text = "Send Data")

        }
        viewModel.response.value?.let { post ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(" Post Created Successfully:", style = MaterialTheme.typography.titleMedium)
            Text(" ID: ${post.id}")
            Text(" Title: ${post.title}")
            Text("🗒 Body: ${post.body}")
        }

    }
}