package rojojun

interface ZettaiHub {
    fun getList(user: User, listName: ListName): ToDoList?
}