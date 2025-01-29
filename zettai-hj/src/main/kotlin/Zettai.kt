package com.rojojun

import com.rojojun.domain.HtmlPage
import com.rojojun.domain.ToDoItem
import com.rojojun.domain.User
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

class Zettai(val lists: Map<User, List<ToDoList>>) : HttpHandler {
    val routes = routes(
        "/todo/{user}/{list}" bind GET to ::showList
    )

    override fun invoke(request: Request): Response = routes(request)

    private fun showList(req: Request): Response =
        req.let(::extractListData)
            .let(::fetchListContent)
            .let(::renderHtml)
            .let(::createResponse)


    fun extractListData(req: Request): Pair<User, ListName> {
        val user: String = req.path("user").orEmpty()
        val list: String = req.path("list").orEmpty()
        return User(user) to ListName(list)
    }

    fun fetchListContent(listId: Pair<User, ListName>): ToDoList =
        lists[listId.first]
            ?.firstOrNull { it.listName == listId.second }
            ?:error("No list with id $listId")

    fun renderHtml(toDoList: ToDoList): HtmlPage =
        HtmlPage("""
        <html>
            <body>
                <h1>Zettai</h1>
                <h2>${toDoList.listName.name}</h2>
                <table>
                    <tbody>
                    ${renderItems(toDoList.items)}
                    </tbody>
                </table>
            </body>
        </html>
    """.trimIndent())

    fun renderItems(items: List<ToDoItem>) =
        items.map {
            """<tr><td>${it.description}</td></tr>""".trimIndent()
        }.joinToString("")


    fun createResponse(html: HtmlPage): Response =
        Response(Status.OK).body(html.raw)
}