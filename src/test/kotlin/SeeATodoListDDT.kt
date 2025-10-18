import com.ubertob.pesticide.core.DDT
import com.ubertob.pesticide.core.DomainDrivenTest

typealias ZettaiDDT = DomainDrivenTest<ZettaiActions>

fun allActions() = setOf(
    DomainOnlyActions(),
    HttpActions()
)

class SeeATodoListDDT: ZettaiDDT(allActions()) {
    val frank by NamedActor(::ToDoListOwner)
    val bob by NamedActor(::ToDoListOwner)

    val shoppingListName = "shopping"
    val shoppingItems = listOf("당근", "사과", "우유")

    val gardeningListName = "gardening"
    val gardenItems = listOf("펜스 고치기", "잡초 고르기")

    @DDT
    fun `리스트의 주인은 본인의 리스트를 볼 수 있다`() = ddtScenario {
        setUp {
            frank.`리스트를 가지고 유저를 생성`(shoppingListName, shoppingItems)
            bob.`리스트를 가지고 유저를 생성`(gardeningListName, gardenItems)
        }.thenPlay(
            frank.`#listnames 의 #listname 을 볼 수 있다`(shoppingListName, shoppingItems),
            bob.`#listnames 의 #listname 을 볼 수 있다`(gardeningListName, gardenItems)
        )
    }
}