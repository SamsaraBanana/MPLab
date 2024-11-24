package com.example.mplab_todoapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.mplab_todoapp.theme.MPLab_ToDoAppTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MPLab_ToDoAppTheme {
                Scaffold (
                    content = { paddingValues ->
                        TodoListScreen(modifier = Modifier.padding(paddingValues))
                    }
                )
            }
        }
    }
}

//Remember -> Speichert den Wert zwischen ReComposes. Bei Recomposes wird die funktion komplett neu gebaut und variablen neu initialisiert.
//MutableStateOf -> Überwacht Compose und benachrichtigt Compose bei änderungen und sorgt für UI rebuilds.

@Composable
fun TodoListScreen(modifier: Modifier = Modifier) {
    // Speicher und greift direkt auf den Wert des Objekts zu. Ist keine direkte Instanz! Sondern nur eine referenz/weiterleitung.
    var showTextField by remember { mutableStateOf(false) }
    // Speichert das gesamten mutableState Objekt. Wird gebraucht um das Objekt an Funktionen weiter zu geben.
    var todoText = remember { mutableStateOf("") }

    // Debugging
    val context = LocalContext.current
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

            }
            if (showTextField) {
                AddTodoDialog(
                    todoText = todoText,
                    onDismiss =
                    {
                        showTextField = false
                        showSimpleToast(context, showTextField.toString())
                    }
                )
            }
        }
        FloatingActionButton(
            onClick =
            {
                showTextField = !showTextField
                showSimpleToast(context, showTextField.toString())
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = if (showTextField) "Close" else "+")
        }
    }
}


@Composable
fun AddTodoDialog(
    todoText: MutableState<String>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Add new To-Do")
        },
        text = {
            Column {
                TextField(
                    value = todoText.value,
                    onValueChange = { todoText.value = it },
                    label = { Text("Enter To-Do") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (todoText.value.isNotBlank()) {
                        onDismiss()
                    }
                    todoText.value =""
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview() {
    MPLab_ToDoAppTheme {
        TodoListScreen()

    }
}

fun showSimpleToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}



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

data class TodoItem (
    val title: String,
    val isCompleted: Boolean
)