package com.rojojun.domain

import com.rojojun.ListName
import com.rojojun.ToDoList

class ToDoListHub(val lists: Map<User, List<ToDoList>>): ZettaiHub {
    override fun getList(user: User, listName: ListName): ToDoList? =
        lists[user]
            ?. firstOrNull { it.listName == listName }


}