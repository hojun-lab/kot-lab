package com.rojojun.domain

import java.time.LocalDate

data class ToDoItem(
    val description: String,
    val dueDate: LocalDate? = null,
    val status: ToDoStatus = ToDoStatus.TODO
)