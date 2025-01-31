package stories

import com.rojojun.domain.User
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import tooling.ApplicationForAT
import tooling.startTheApplication

class SeeATodoListAT {
    val frank = ToDoListOwner("Frank")
    val shoppingItems = listOf("carrots", "apples", "milk")
    val frankList = createList("shopping", shoppingItems)

    val bob = ToDoListOwner("Bob")
    val gardenItems = listOf("fix the fence", "mowing the lawn")
    val bobList = createList("gardening", gardenItems)

    val lists = mapOf(
        frank.asUser() to listOf(frankList),
        bob.asUser() to listOf(bobList)
    )

    private fun ToDoListOwner.asUser(): User = User(name)

    @Test
    fun `TODO 리스트의 주인은 리스트를 확인 할 수 있다`() {
        val app = startTheApplication(lists)
        app.runScenario (
            frank.`리스트를 볼 수 있다`("shopping", shoppingItems),
            bob.`리스트를 볼 수 있다`("gardening", gardenItems)
        )
    }

    @Test
    fun `자기 자신의 할 일만 보인다`() {
        val app = startTheApplication(lists)
        app.runScenario(
            frank.`리스트를 볼 수 없다`("gardening"),
            bob.`리스트를 볼 수 없다`("shopping")
        )
    }
}