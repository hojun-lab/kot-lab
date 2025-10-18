package rojojun

import org.http4k.core.*
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    val hub = ToDoListHub(mutableMapOf())

    val items = listOf("이직 준비", "화이팅", "하하하...")
    val toDoList = ToDoList(ListName("book"), items.map(::ToDoItem))
    val lists = mapOf(User("Hojun") to listOf(toDoList))
    val app: HttpHandler = Zettai(hub)
    app.asServer(Jetty(8080)).start()
    println("server start at http://localhost:8080/todo/Hojun/book")
}