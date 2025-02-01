package com.rojojun.domain

import com.rojojun.ListName
import com.rojojun.ToDoList

interface ZettaiHub {
    fun getList(user: User, listName: ListName): ToDoList?
}