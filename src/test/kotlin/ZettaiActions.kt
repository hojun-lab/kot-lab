import com.ubertob.pesticide.core.DdtActions
import com.ubertob.pesticide.core.DdtProtocol
import rojojun.ListName
import rojojun.ToDoList
import rojojun.User

interface ZettaiActions: DdtActions<DdtProtocol> {
    fun getToDoList(user: User, listName: ListName): ToDoList?

    fun ToDoListOwner.`리스트를 가지고 유저를 생성`(listName: String, items: List<String>)
}