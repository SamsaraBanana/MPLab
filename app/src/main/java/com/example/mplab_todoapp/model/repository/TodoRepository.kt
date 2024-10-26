package com.example.mplab_todoapp.model.repository

import com.example.mplab_todoapp.model.data.TodoItem

class TodoRepository {
    private val todoList = mutableListOf<TodoItem>()

    fun getTodoList() = todoList

    fun addTodoItem(item: TodoItem) {
        todoList.add(item)
    }

    fun updateTodoItem(updatedItem: TodoItem) {
        val index = todoList.indexOfFirst { it.id == updatedItem.id }
        if (index != -1) todoList[index] = updatedItem
    }
}
