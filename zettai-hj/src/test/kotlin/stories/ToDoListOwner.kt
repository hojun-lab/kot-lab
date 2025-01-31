package stories

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.ToDoItem
import com.rojojun.domain.User
import org.opentest4j.AssertionFailedError
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import tooling.ScenarioActor
import tooling.Step

class ToDoListOwner(override val name: String): ScenarioActor {
    val user = User(name)

    fun `리스트를 볼 수 있다`(listName: String, items: List<String>): Step = {
        val expectedList = createList(listName, items)
        val list = getToDoList(name, listName)
            expectThat(list).isEqualTo(expectedList)
    }

//    fun `리스트를 볼 수 없다`(listName: String, app: ApplicationForAT) {
//        expectThrows<AssertionFailedError> {
//            app.getToDoList(name, listName)
//        }
//    }

    fun `리스트를 볼 수 없다`(listName: String): Step = {
        expectThrows<AssertionFailedError> {
            getToDoList(name, listName)
        }
    }
}

fun createList(listName: String, items: List<String>) =
    ToDoList(ListName(listName), items.map(::ToDoItem))
