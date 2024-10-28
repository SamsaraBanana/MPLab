package com.example.mplab_todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mplab_todoapp.model.TodoItem

class TodoViewModel() : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    fun addTodoItem(title: String) {
        val currentList = _todoList.value ?: emptyList() // Is todoList Null ?: No? Do left operation. Yes? Do right operation
        val updatedList = currentList + TodoItem(title = title, isCompleted = false)
        // Set value to refresh UI.
        _todoList.value = updatedList
    }

    fun deleteTodoItem(item: TodoItem) {
        val currentList = _todoList.value ?: return // Is todoList Null ?: No? Do left operation. Yes? Do right operation
        val updatedList = currentList.toMutableList().apply {
            remove(item)
        }
        _todoList.value = updatedList
    }

    fun toggleCompletion(item: TodoItem) {
        val currentList = _todoList.value ?: return // Is todoList Null ?: No? Do left operation. Yes? Do right operation
        val updatedList = currentList.map {
            if (it.title == item.title) it.copy(isCompleted = !it.isCompleted) else it
        }
        // Set value to refresh UI.
        _todoList.value = updatedList
    }
}
