package com.rojojun.webServer

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.ToDoItem
import com.rojojun.domain.User
import org.http4k.core.HttpHandler
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger("main")

fun main() {
    val items = listOf("write chapter", "insert code", "draw diagram")
    val toDoList = ToDoList(ListName("book"), items.map(::ToDoItem))
    val lists = mapOf(User("hojun") to listOf(toDoList))
    val app: HttpHandler = Zettai(lists)
    app.asServer(Jetty(8080)).start()
    logger.info("Server started at http://localhost:8080")
}