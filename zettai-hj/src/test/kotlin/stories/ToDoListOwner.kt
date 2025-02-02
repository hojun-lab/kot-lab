package stories

import com.rojojun.ListName
import com.rojojun.ToDoList
import com.rojojun.domain.ToDoItem
import com.rojojun.domain.User
import com.ubertob.pesticide.core.DdtActor
import org.opentest4j.AssertionFailedError
import strikt.api.Assertion
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.containsExactlyInAnyOrder
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import tooling.ScenarioActor
import tooling.Step
import tooling.ZettaiActions

class ToDoListOwner(override val name: String): DdtActor<ZettaiActions>() {
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

    fun `#listnames 에 속한  #listname 을 볼 수 있다`(
        listName: String,
        expctedItems: List<String>) =
        step(listName, expctedItems) {
            val list = getToDoList(user, ListName(listName))

            expectThat(list)
                .isNotNull()
                .itemNames
                .containsExactlyInAnyOrder(expctedItems)
        }

    private val Assertion.Builder<ToDoList>.itemNames
        get() = get { items.map { it.description } }
}

fun createList(listName: String, items: List<String>) =
    ToDoList(ListName(listName), items.map(::ToDoItem))
