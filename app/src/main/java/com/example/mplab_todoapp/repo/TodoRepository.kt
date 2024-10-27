package com.example.mplab_todoapp.repo

import com.example.mplab_todoapp.model.TodoItem

class TodoRepository {
    private val todoList = mutableListOf<TodoItem>()

    fun getTodoList() = todoList

    fun addTodoItem(item: TodoItem) {
        todoList.add(item)
    }

    fun updateTodoItem(updatedItem: TodoItem) {
        val index = todoList.indexOfFirst { it.title == updatedItem.title }
        if (index != -1) todoList[index] = updatedItem
    }
}
