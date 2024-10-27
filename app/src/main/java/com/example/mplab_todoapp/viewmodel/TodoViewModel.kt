package com.example.mplab_todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mplab_todoapp.model.TodoItem
import com.example.mplab_todoapp.repo.TodoRepository

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    init {
        _todoList.value = repository.getTodoList()
    }

    fun addTodoItem(title: String) {

        repository.addTodoItem(TodoItem(title = title,isCompleted = false))
        _todoList.value = repository.getTodoList()
    }

    fun toggleCompletion(item: TodoItem) {
        val currentList = _todoList.value ?: return
        val updatedList = currentList.map {
            if (it.title == item.title) it.copy(isCompleted = !it.isCompleted) else it
        }
        // Setze die aktualisierte Liste neu, um die UI zu benachrichtigen
        _todoList.value = updatedList
    }
}
