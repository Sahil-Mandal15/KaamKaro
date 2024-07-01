@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kaamkaro.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kaamkaro.model.TodoList
import com.example.kaamkaro.screens.ToDoViewModel
import java.util.UUID


@Composable
fun fullScreenDialog(
    openDialog: MutableState<Boolean>,
    todoViewModel: ToDoViewModel = viewModel(),
) {

    var task by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
        shape = RoundedCornerShape(16.dp),
        onDismissRequest = { openDialog.value = false },
        title = {
            Text(
                text = "Add Task", modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        },
        text = {
            Column(modifier = Modifier.padding(10.dp)) {

                TextField(
                    value = task,
                    onValueChange = { newTask ->
                        task = newTask
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (task.text.isNotEmpty()) {
                        todoViewModel.addTask(
                            TodoList(
                                id = UUID.randomUUID().toString(),
                                task = task.text,
                                isComplete = false
                            )
                        )
                        openDialog.value = false
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Green),
            ) {
                Text(text = "ADD", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                onClick = { openDialog.value = false },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text(text = "CANCEL", color = Color.White)
            }
        }
    )
}


