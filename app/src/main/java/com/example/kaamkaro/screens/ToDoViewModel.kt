package com.example.kaamkaro.screens

import androidx.lifecycle.ViewModel
import com.example.kaamkaro.model.TodoList

class ToDoViewModel: ViewModel() {

    private var todoList = mutableListOf<TodoList>()

    fun getTasks(): List<TodoList> {
        return todoList
    }

    fun addTask(todoItem: TodoList) {
        todoList.add(todoItem)
    }

    fun removeTask(todoItem: TodoList) {
        todoList.remove(todoItem)
    }

    fun markAsComplete(todoItem: TodoList, value: Boolean) {
        val index = todoList.indexOf(todoItem);

        todoList[index] = todoList[index].let {
            it.copy(
                id = it.id,
                task = it.task,
                isComplete = value
            )
        }
    }
}