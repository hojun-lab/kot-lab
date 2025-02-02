package domain

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.User

typealias ToDoListFetcher = (user: User, listName: ListName) -> ToDoList?

interface ToDoListUpdatableFetcher : ToDoListFetcher {
    override fun invoke(user: User, listName: ListName): ToDoList?
    fun assignListToUser(user: User, list: ToDoList): ToDoList?
}