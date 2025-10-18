import com.ubertob.pesticide.core.DdtActions
import com.ubertob.pesticide.core.DdtProtocol
import com.ubertob.pesticide.core.DomainSetUp
import com.ubertob.pesticide.core.Http
import com.ubertob.pesticide.core.Ready
import org.http4k.client.JettyClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.server.Jetty
import org.http4k.server.asServer
import rojojun.ListName
import rojojun.ToDoList
import rojojun.ToDoListHub
import rojojun.User
import rojojun.Zettai

class HttpActions(val env: String = "local"): ZettaiActions {
    private val lists: MutableMap<User, List<ToDoList>> = mutableMapOf()
    private val hub = ToDoListHub(lists)

    override val protocol: DdtProtocol = Http(env)

    val zettaiPort = 8000
    val server = Zettai(hub).asServer(Jetty(zettaiPort))
    val client = JettyClient()

    override fun prepare(): DomainSetUp {
        server.start()
        return Ready
    }

    override fun tearDown(): HttpActions =
        also { server.stop() }

    private fun callZettai(method: Method, path: String): Response =
        client(log(Request(method, "http://localhost:$zettaiPort/$path")))

    override fun getToDoList(user: User, listName: ListName): ToDoList? =
        hub.getList(user, listName)

    override fun ToDoListOwner.`리스트를 가지고 유저를 생성`(
        listName: String,
        items: List<String>
    ) {
        val todoList = createList(listName, items)
        val existingLists = lists[user] ?: emptyList()
        lists[user] = existingLists + todoList
    }

    fun <T> log(something: T): T {
        println("--- $something")
        return something
    }
}