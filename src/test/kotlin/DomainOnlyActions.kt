import com.ubertob.pesticide.core.DdtProtocol
import com.ubertob.pesticide.core.DomainOnly
import com.ubertob.pesticide.core.Ready
import rojojun.ListName
import rojojun.ToDoList
import rojojun.ToDoListHub
import rojojun.User

class DomainOnlyActions(): ZettaiActions {
    override val protocol: DdtProtocol = DomainOnly

    override fun prepare() = Ready

    override fun getToDoList(user: User, listName: ListName): ToDoList? =
        hub.getList(user, listName)

    override fun ToDoListOwner.`리스트를 가지고 유저를 생성`(
        listName: String,
        items: List<String>
    ) {
        TODO("Not yet implemented")
    }

    private val lists: Map<User, List<ToDoList>> = emptyMap()

    private val hub = ToDoListHub(lists)
}