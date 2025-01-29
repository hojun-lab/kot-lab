package com.rojojun

import com.rojojun.domain.ToDoItem

data class ToDoList(
    val listName: ListName,
    val items: List<ToDoItem>
)
