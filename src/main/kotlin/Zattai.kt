package rojojun

import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import rojojun.function.andThen

data class Zattai(val lists: Map<User,List<ToDoList>>): HttpHandler {
    val routes = routes(
        "/todo/{user}/{list}" bind Method.GET to ::showList
    )

    override fun invoke(request: Request): Response = routes(request)

    val processFun = ::extractListData andThen
            ::fetchListContent andThen
            ::renderHtml andThen
            ::createResponse

    private fun showList(req: Request): Response = processFun(req)

    fun extractListData(request: Request): Pair<User, ListName> {
        val user: String = request.path("user").orEmpty()
        val list: String = request.path("list").orEmpty()
        return User(user) to ListName(list)
    }
    fun fetchListContent(listId: Pair<User, ListName>): ToDoList =
        lists[listId.first]
            ?.firstOrNull { it.listName == listId.second }
            ?: error("알 수 없는 리스트입니다.")

    fun renderHtml(list: ToDoList): HtmlPage = HtmlPage(
        """
        <html>
            <body>
                <h1>Zettai</h1>
                <h2>${list.listName.name}</h2>
                <table>
                    <tbody>${renderItems(list.items)}</tbody>
                </table>
            </body>
        </html>
    """
    )

    fun createResponse(html: HtmlPage): Response =
        Response(Status.OK).body(html.raw)

    private fun renderItems(items: List<ToDoItem>) =
        items.joinToString("") {
            """
                <tr>
                    <td>
                    ${it.description}
                    </td>
                </tr>
            """.trimIndent()
        }
}