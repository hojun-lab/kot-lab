package tooling;

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.Zettai
import com.rojojun.domain.ToDoItem
import com.rojojun.domain.User
import org.http4k.client.JettyClient
import org.http4k.core.*
import org.http4k.filter.ClientFilters
import org.http4k.server.Jetty
import org.http4k.server.asServer
import kotlin.test.fail

class ApplicationForAT(val client: HttpHandler, val server: AutoCloseable): Actions {
    override fun getToDoList(user: String, listName: String): ToDoList {
        val response = client(Request(Method.GET, "/todo/$user/$listName"))

        return if (response.status == Status.OK)
            parseResponse(response.bodyString())
        else
            fail(response.toMessage())
    }

    fun runScenario(vararg steps: Step) {
        server.use {
            steps.forEach { step -> step(this) }
        }
    }

    private fun parseResponse(html: String): ToDoList {
        val nameRegex = "<h2>.*<".toRegex()
        val listName = ListName(extractListName(nameRegex, html))
        val itemsRegex = "<td>.*?<".toRegex()
        val items = itemsRegex.findAll(html)
            .map { ToDoItem(extractItemDesc(it)) }.toList()
        return ToDoList(listName, items)
    }

    private fun extractListName(nameRegex: Regex, html: String): String =
        nameRegex.find(html) ?.value
            ?.substringAfter("<h2>")
            ?.dropLast(1)
            .orEmpty()

    private fun extractItemDesc(matchResult: MatchResult): String =
        matchResult.value.substringAfter("<td>").dropLast(1)
}

fun startTheApplication(lists: Map<User, List<ToDoList>>): ApplicationForAT {
    val port = 8081 // 메인 포트와 다름
    val server = Zettai(lists).asServer(Jetty(port))
    server.start()
    val client = ClientFilters
        .SetBaseUriFrom(Uri.of("http://localhost:$port/"))
        .then(JettyClient())
    return ApplicationForAT(client, server)
}
