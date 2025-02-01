package tooling

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.User
import com.ubertob.pesticide.core.DdtActions
import com.ubertob.pesticide.core.DdtProtocol

interface ZettaiActions: DdtActions<DdtProtocol> {
    fun getToDoList(user: User, listName: ListName): ToDoList?
}

typealias Step = ZettaiActions.() -> Unit