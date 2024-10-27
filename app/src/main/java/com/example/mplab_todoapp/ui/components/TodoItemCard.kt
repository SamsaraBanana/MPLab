package com.example.mplab_todoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mplab_todoapp.model.TodoItem
import com.example.mplab_todoapp.ui.theme.MPLab_ToDoAppTheme


@Composable
fun TodoItemCard(item: TodoItem, onItemClick: (TodoItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(item) }
    ) {
        Checkbox(
            checked = item.isCompleted,
            onCheckedChange = { /* Callback for click handling */ }
        )
        Text(text = item.title)
    }
}


@Preview(showBackground = true)
@Composable
fun TodoItemCardPreview() {
    MPLab_ToDoAppTheme {
        val todoItem = TodoItem("123", false)
        TodoItemCard(todoItem, onItemClick = { })
    }
}