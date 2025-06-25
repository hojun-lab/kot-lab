import org.http4k.client.JettyClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import rojojun.*
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SeeATodoListAT {
    @Test
    fun `리스트의 주인은 본인의 리스트를 볼 수 있다`() {
        val user = "frank"
        val listName = "shopping"
        val foodToBuy = listOf("당근", "사과", "우유")
        startApplication(user, listName, foodToBuy)
        val list = getToList(user, listName)
        expectThat(list.listName.name).isEqualTo(listName)
        expectThat(list.items.map { it.description }).isEqualTo(foodToBuy)
    }

    fun getToList(user: String, listName: String): ToDoList {
        val client = JettyClient()
        val request = Request(Method.GET,
            "http://localhost:8081/todo/$user/$listName")
        val response = client(request)
        return if (response.status == Status.OK)
            parseResponse(response.toString())
        else
            fail(response.toMessage())
    }

    fun parseResponse(html: String): ToDoList {
        val nameRegex = "<h2>.*<".toRegex()
        val listName = ListName(extractListName(nameRegex, html))
        val itemRegex = "<td>.*?<".toRegex()
        val items = itemRegex.findAll(html)
            .map { ToDoItem(extractItemDesc(it)) }.toList()
        return ToDoList(listName, items)
    }

    private fun extractListName(nameRegex: Regex, html: String): String =
        nameRegex.find(html)?.value
            ?.substringAfter("<h2>")
            ?.dropLast(1)
            .orEmpty()

    private fun extractItemDesc(matchResult: MatchResult): String =
        matchResult.value.substringAfter("<td>").dropLast(1)

    private fun startApplication(
        user: String,
        listName: String,
        items: List<String>
    ) {
        val toDoList = ToDoList(ListName(listName), items.map(::ToDoItem))
        val lists = mapOf(User(user) to listOf(toDoList))
        val server = Zattai(lists).asServer(Jetty(8081))
        server.start()
    }
}