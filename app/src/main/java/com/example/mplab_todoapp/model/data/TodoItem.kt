package com.example.mplab_todoapp.model.data

/**
 * Eine data class wird speziell für einfache Datencontainer verwendet
 * und bringt automatisch einige nützliche Funktionen mit,
 * während eine normale Klasse diese zusätzlichen Funktionen nicht standardmäßig bietet.
 *
 * equals()
 * toString()
 * copy()
 * ....
 */
data class TodoItem (
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

