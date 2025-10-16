import rojojun.ListName
import rojojun.ToDoItem
import rojojun.ToDoList
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ToDoListOwner(override val name: String) : ScenarioActor {
    fun canSeeTheList(listName: String, items: List<String>) {
        val expectedList = createList(listName, items)
        val list = getToDoLList(name, listName)
            expectThat(list).isEqualTo(expectedList)
    }

    private fun getToDoLList(name: String, listName: String): ToDoList {
        TODO("Not yet implemented")
    }
}

private fun createList(listName: String, items: List<String>) =
    ToDoList(ListName(listName), items.map(::ToDoItem))