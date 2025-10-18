import org.opentest4j.AssertionFailedError
import rojojun.ListName
import rojojun.ToDoItem
import rojojun.ToDoList
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

class ToDoListOwner(override val name: String) : ScenarioActor {
    fun canSeeTheList(listName: String, items: List<String>, app : ApplicationForAT) {
        val expectedList = createList(listName, items)
        val list = app.getToDoList(name, listName)
        expectThat(list).isEqualTo(expectedList)
    }

    fun canSeeTheList(listName: String, items: List<String>): Step = {
        val expectedList = createList(listName, items)
//        expectThat(items).isEqualTo(expectedList)
    }


    fun cannotSeeTheList(listName: String): Step = {
        expectThrows<AssertionFailedError> {
            getToDoList(name, listName)
        }
    }

    private fun getToDoList(name: String, listName: String): ToDoList {
        TODO("Not yet implemented")
    }
}

fun createList(listName: String, items: List<String>) =
    ToDoList(ListName(listName), items.map(::ToDoItem))