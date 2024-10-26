package com.example.mplab_todoapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mplab_todoapp.model.data.TodoItem
import com.example.mplab_todoapp.model.repository.TodoRepository
import com.example.mplab_todoapp.ui.components.TodoItemCard
import com.example.mplab_todoapp.ui.theme.MPLab_ToDoAppTheme
import com.example.mplab_todoapp.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(viewModel: TodoViewModel , modifier: Modifier = Modifier) {
    val todoList by viewModel.todoList.observeAsState(emptyList())
    // Zustand für die Sichtbarkeit des Textfelds verwalten
    var showTextField by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "ToDo App",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(todoList) { item ->
                    TodoItemCard(item = item, onItemClick = { viewModel.toggleCompletion(item) })
                }
            }
            if (showTextField) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text("Write ToDo") }
                )
            }

        }
        FloatingActionButton(
            onClick = { showTextField = !showTextField },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = if (showTextField) "Close" else "+")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview() {
    MPLab_ToDoAppTheme {
        TodoListScreen(viewModel = previewTodoViewModel())
    }
}

@Composable
fun previewTodoViewModel(): TodoViewModel {
    val dummyRepository = TodoRepository().apply {
        addTodoItem(TodoItem(id = 1, title = "Sample Task 1", isCompleted = false))
        addTodoItem(TodoItem(id = 2, title = "Sample Task 2", isCompleted = true))
    }
    return TodoViewModel(dummyRepository)
}