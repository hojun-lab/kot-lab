package com.rojojun

import com.rojojun.domain.ToDoItem
import java.util.*

fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}

val pathElementPattern = Regex(pattern = "[A-Za-z0-9]+")

data class ToDoList(
    val listName: ListName,
    val items: List<ToDoItem>
) {
    companion object {
        fun build(
            listName: String, items: List<String>
        ): ToDoList = ToDoList(ListName.fromUntrustedOrThrow(listName), items.map() { ToDoItem(it) })
    }
}
