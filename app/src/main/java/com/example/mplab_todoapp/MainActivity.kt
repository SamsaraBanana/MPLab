package com.example.mplab_todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mplab_todoapp.model.repository.TodoRepository
import com.example.mplab_todoapp.ui.screens.TodoListScreen
import com.example.mplab_todoapp.ui.theme.MPLab_ToDoAppTheme
import com.example.mplab_todoapp.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MPLab_ToDoAppTheme {
                Scaffold(
                    content = { paddingValues ->
                        TodoListScreen(viewModel = TodoViewModel(TodoRepository()), modifier = Modifier.padding(paddingValues))
                    }
                )
            }
        }
    }
}