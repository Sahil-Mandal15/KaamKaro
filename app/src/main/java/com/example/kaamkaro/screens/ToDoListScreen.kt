package com.example.kaamkaro.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ToDoListScreen(toDoViewModel: ToDoViewModel = viewModel()) {


        if (toDoViewModel.getTasks().isEmpty()) {

            Text(
                text = "Nothing Here", textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(), color = Color.Black,
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(toDoViewModel.getTasks()) {
                    Log.e("todoList", "" + it.isComplete)

                    Card(
                        colors = CardDefaults.cardColors(Color.Gray),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        modifier = Modifier.padding(top = 63.dp, start = 3.dp, end = 3.dp)
                    ) {

                        Row(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = it.task,
                                textDecoration =
                                if (it.isComplete) TextDecoration.LineThrough else null
                            )
                            Spacer(modifier = Modifier.fillMaxSize(0.7f))

                            Checkbox(
                                checked = it.isComplete,
                                onCheckedChange = { value ->
                                    toDoViewModel.markAsComplete(
                                        todoItem = it,
                                        value = !value,
                                    )
                                },
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Filled.Delete, contentDescription = null,
                                modifier = Modifier.clickable {
                                    toDoViewModel.removeTask(it)
                                },
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }

