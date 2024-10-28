package com.example.mplab_todoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mplab_todoapp.model.TodoItem
import com.example.mplab_todoapp.ui.theme.MPLab_ToDoAppTheme


@Composable
fun TodoItemCard(
    item: TodoItem,
    onItemClick: (TodoItem) -> Unit,
    onDeleteClick: (TodoItem) -> Unit
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isCompleted,
            onCheckedChange = { onItemClick(item) },
        )
        Text(
            text = item.title,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f) // Fills the center card
        )
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Todo",
            tint = Color.Red,
            modifier = Modifier
                .clickable { onDeleteClick(item) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TodoItemCardPreview() {
    MPLab_ToDoAppTheme {
        val todoItem = TodoItem("1223", false)
        TodoItemCard(todoItem, onItemClick = { }, onDeleteClick = {})
    }
}