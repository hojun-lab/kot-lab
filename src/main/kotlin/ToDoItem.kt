package rojojun

import jdk.jfr.Description

data class ToDoItem(val description: String)
enum class ToDoStatus {
    TODO, IN_PROGRESS, DONE, BLOCKED
}