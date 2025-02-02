package com.rojojun.webServer

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.ToDoItem
import com.rojojun.domain.ToDoListHub
import com.rojojun.domain.ToDoStatus
import com.rojojun.domain.User
import domain.ToDoListFetcher
import domain.ToDoListFetcherFromMap
import domain.ToDoListStore
import org.http4k.core.HttpHandler
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import webServer.Routes
import java.time.LocalDate

val logger: Logger = LoggerFactory.getLogger("main")

fun main() {
    val fetcher = ToDoListFetcherFromMap(storeWithExampleData())
    val hub = ToDoListHub(fetcher)

//    val items = listOf("write chapter", "insert code", "draw diagram")
//    val toDoList = ToDoList(ListName("book"), items.map(::ToDoItem))
//    val lists = mapOf(User("hojun") to listOf(toDoList))
//    val app: HttpHandler = Zettai(lists)

    Routes(hub = hub).asServer(Jetty(8080)).start()
    logger.info("Server started at http://localhost:8080")
}

fun storeWithExampleData() : ToDoListStore = mutableMapOf(
    User("hojun") to
            mutableMapOf(exampleToDoList().listName to exampleToDoList())
)

private fun exampleToDoList(): ToDoList {
    return ToDoList(
        listName = ListName.fromTrusted("book"),
        items = listOf(
            ToDoItem("prepare the diagram", LocalDate.now().plusDays(1), ToDoStatus.DONE),
            ToDoItem("rewrite explanations", LocalDate.now().plusDays(2), ToDoStatus.IN_PROGRESS),
            ToDoItem("finish the chapter"),
            ToDoItem("draft next chapter")            )
    )
}