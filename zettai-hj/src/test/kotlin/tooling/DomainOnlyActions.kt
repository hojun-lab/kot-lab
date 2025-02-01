package tooling

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.ToDoListHub
import com.rojojun.domain.User
import com.ubertob.pesticide.core.DdtProtocol
import com.ubertob.pesticide.core.DomainOnly
import com.ubertob.pesticide.core.Ready

class DomainOnlyActions() : ZettaiActions {
    override val protocol: DdtProtocol = DomainOnly
    override fun prepare() = Ready

    private val lists: Map<User, List<ToDoList>> = emptyMap();

    private val hub = ToDoListHub(list)

    override fun getToDoList(user: User, listName: ListName): ToDoList? =
        hub.getList(user, listName)
}
