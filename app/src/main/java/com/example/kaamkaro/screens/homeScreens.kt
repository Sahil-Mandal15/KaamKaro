package com.example.kaamkaro.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kaamkaro.components.fullScreenDialog

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun homeScreen() {

    val openDialog = remember { mutableStateOf(false) }
    val todoViewModel by remember { mutableStateOf( ToDoViewModel() ) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { openDialog.value = true; }
            ) {
               Icon(
                   imageVector = Icons.Filled.Add,
                   contentDescription = null,

               )
                if (openDialog.value) {
                    fullScreenDialog(
                        openDialog = openDialog,
                        todoViewModel = todoViewModel
                    )
                }

            }
        },
        topBar = {
            TopAppBar(title = { Text(text = "KaamKaro",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 121.dp),
                )})
        }
    ) {

        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToDoListScreen(todoViewModel)
        }
    }

    }