import org.http4k.client.JettyClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.junit.jupiter.api.fail
import rojojun.ListName
import rojojun.ToDoItem
import rojojun.ToDoList
import rojojun.User
import rojojun.Zattai

class ApplicationForAT(val client: HttpHandler, val server: AutoCloseable): Actions {
    override fun getToDoList(user: String, listName: String): ToDoList {
        val response = client(Request(Method.GET, "/todo/$user/$listName"))

        return if (response.status == Status.OK) {
            parseResponse(response.bodyString())
        } else {
            fail(response.toMessage())
        }
    }

    fun runScenario(vararg steps: Step) {
        server.use {
            steps.onEach { step -> step(this) }
        }
    }

    fun parseResponse(html: String): ToDoList {
        val nameRegex = "<h2>(.*?)</h2>".toRegex(RegexOption.DOT_MATCHES_ALL)
        val listName = ListName(extractListName(nameRegex, html))
        val itemRegex = "<td>\\s*(.*?)\\s*</td>".toRegex(RegexOption.DOT_MATCHES_ALL)
        val items = itemRegex.findAll(html)
            .map { ToDoItem(extractItemDesc(it)) }.toList()
        return ToDoList(listName, items)
    }

    private fun extractListName(nameRegex: Regex, html: String): String =
        nameRegex.find(html)?.groupValues?.get(1)?.trim().orEmpty()

    private fun extractItemDesc(matchResult: MatchResult): String =
        matchResult.groupValues[1].trim()
}

fun startTheApplication(lists: Map<User, List<ToDoList>>): ApplicationForAT {
    val port = 8081
    val server = Zattai(lists).asServer(Jetty(port))
    server.start()
    val client = ClientFilters
        .SetBaseUriFrom(Uri.of("http://localhost:$port/"))
        .then(JettyClient())
    return ApplicationForAT(client, server)
}