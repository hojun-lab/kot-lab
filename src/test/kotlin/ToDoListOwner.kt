import com.ubertob.pesticide.core.DdtActor
import org.opentest4j.AssertionFailedError
import rojojun.ListName
import rojojun.ToDoItem
import rojojun.ToDoList
import rojojun.User
import strikt.api.Assertion
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.all
import strikt.assertions.containsExactlyInAnyOrder
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

//class ToDoListOwner(override val name: String) : ScenarioActor {
//    fun canSeeTheList(listName: String, items: List<String>, app : ApplicationForAT) {
//        val expectedList = createList(listName, items)
//        val list = app.getToDoList(name, listName)
//        expectThat(list).isEqualTo(expectedList)
//    }
//
//    fun canSeeTheList(listName: String, items: List<String>): Step = {
//        val expectedList = createList(listName, items)
////        expectThat(items).isEqualTo(expectedList)
//    }
//
//
//    fun cannotSeeTheList(listName: String): Step = {
//        expectThrows<AssertionFailedError> {
//            getToDoList(name, listName)
//        }
//    }
//
//    private fun getToDoList(name: String, listName: String): ToDoList {
//        TODO("Not yet implemented")
//    }
//}

class ToDoListOwner(override val name: String) : DdtActor<ZettaiActions>() {
    val user = User(name)

    fun `#listnames 의 #listname 을 볼 수 있다`(listName: String, expectedItems: List<String>) =
        step(listName, expectedItems) {
            val list = getToDoList(user, ListName(listName))

            expectThat(list)
                .isNotNull()
                .itemNames
                .containsExactlyInAnyOrder(expectedItems)
        }

    private val Assertion.Builder<ToDoList>.itemNames
            get() = get { items.map { it.description } }
}

fun createList(listName: String, items: List<String>) =
    ToDoList(ListName(listName), items.map(::ToDoItem))