package domain

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.User

typealias ToDoListStore = MutableMap<User, MutableMap<ListName, ToDoList>>

class ToDoListFetcherFromMap(
    private val store: ToDoListStore
) : ToDoListUpdatableFetcher {
    override fun invoke(user: User, listName: ListName): ToDoList? =
        store[user]?.get(listName)

    override fun assignListToUser(user: User, list: ToDoList): ToDoList? =
        store.compute(user) {_, value ->
            val listMap = value ?: mutableMapOf()
            listMap.apply { put(list.listName, list) }
        }?.let { list }
}