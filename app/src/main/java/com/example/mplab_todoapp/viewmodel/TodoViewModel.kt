package com.example.mplab_todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mplab_todoapp.model.data.TodoItem
import com.example.mplab_todoapp.model.repository.TodoRepository

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    init {
        _todoList.value = repository.getTodoList()
    }

    fun addTodoItem(item: TodoItem) {
        repository.addTodoItem(item)
        _todoList.value = repository.getTodoList()
    }

    fun toggleCompletion(item: TodoItem) {
        val updatedItem = item.copy(isCompleted = !item.isCompleted)
        repository.updateTodoItem(updatedItem)
        _todoList.value = repository.getTodoList()
    }
}
