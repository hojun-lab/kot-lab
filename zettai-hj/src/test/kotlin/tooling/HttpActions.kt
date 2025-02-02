package tooling

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.ToDoListHub
import com.rojojun.domain.User
import com.ubertob.pesticide.core.*
import org.http4k.client.JettyClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.server.Jetty
import org.http4k.server.asServer

class HttpActions(val env: String = "local"): ZettaiActions {
    private val fetcher = ToDoList
    private val hub = ToDoListHub(fetcher)

    override val protocol: DdtProtocol
        get() = Http(env)

    val zettaiPort = 8000

    val server = Zettai(hub).asServer(Jetty(zettaiPort))
    val client = JettyClient(server)

    override fun prepare(): DomainSetUp {
        server.start()
        return Ready
    }

    override fun tearDown(): DdtActions<DdtProtocol> =
        also { server.stop() }

    private fun callZettai(method: Method, path: String): Response =
        client(
            log(Request(method, "http://localhost:$zettaiPort/$path"))
        )

    override fun getToDoList(user: User, listName: ListName): ToDoList? {
        TODO("Not yet implemented")
    }
}