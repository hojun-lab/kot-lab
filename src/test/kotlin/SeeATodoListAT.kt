import org.junit.jupiter.api.Test
import rojojun.User

class SeeATodoListAT {
    val frank = ToDoListOwner("Frank")
    val shoppingItems = listOf("당근", "사과", "우유")
    val frankList = createList("shopping", shoppingItems)

    val bob = ToDoListOwner("Bob")
    val gardenItems = listOf("펜스 고치기", "잡초 고르기")
    val bobList = createList("gardening", gardenItems)

    val lists = mapOf(
        frank.asUser() to listOf(frankList),
        bob.asUser() to listOf(bobList),
    )

    fun ToDoListOwner.asUser(): User = User(name)

    @Test
    fun `리스트의 주인은 본인의 리스트를 볼 수 있다`() {
        val app = startTheApplication(lists)
        app.runScenario(
            frank.canSeeTheList("shopping", shoppingItems),
            bob.canSeeTheList("gardening", gardenItems)
        )
    }

    @Test
    fun `자신의 리스트만 볼 수 있다`() {
        val app = startTheApplication(lists)
        app.runScenario(
            frank.cannotSeeTheList("gardening"),
            bob.cannotSeeTheList("shopping")
        )
    }
}