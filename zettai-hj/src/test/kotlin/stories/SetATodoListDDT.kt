package stories

import com.ubertob.pesticide.core.DDT
import com.ubertob.pesticide.core.DomainDrivenTest
import tooling.DomainOnlyActions
import tooling.HttpActions
import tooling.ZettaiActions

typealias ZettaiDDT = DomainDrivenTest<ZettaiActions>

fun allActions() = setOf(
    DomainOnlyActions(),
    HttpActions()
)

class SetATodoListDDT : ZettaiDDT(allActions()) {
    val frank by NamedActor(::ToDoListOwner)
    val bob by NamedActor(::ToDoListOwner)

    val shoppingListName = "shopping"
    val shoppingItems = listOf("carrots", "apples", "milk")

    val gardenListName = "gardening"
    val gardenItems = listOf("fix the fence", "mowing the lawn")

    @DDT
    fun `리스트 주인은 자신의 리스트를 볼 수 있다`() = ddtScenario {
        setUp {
            frank.`리스트를 볼 수 있다`("shopping", shoppingItems),
            bob.`리스트를 볼 수 있다`("gardening", gardenItems)
        }.thenPlay(
            frank.`#listnames 에 속한  #listname 을 볼 수 있다`(
                shoppingListName, shoppingItems
            ),
            bob.`#listnames 에 속한  #listname 을 볼 수 있다`(
                shoppingListName, shoppingItems
            )
        )
    }
}