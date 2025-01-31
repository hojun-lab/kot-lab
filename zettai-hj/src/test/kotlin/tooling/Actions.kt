package tooling

import com.rojojun.ToDoList

interface Actions {
    fun getToDoList(user: String, list: String): ToDoList?
}

typealias Step = Actions.() -> Unit