package rojojun

class ToDoListHub(val lists: Map<User, List<ToDoList>>): ZettaiHub {
    override fun getList(user: User, listName: ListName): ToDoList? =
       lists[user]?.firstOrNull() { it.listName == listName }
}