import rojojun.ToDoList

interface Actions {
    fun getToDoList(user: String , listName: String): ToDoList?
}

typealias Step = Actions.() -> Unit