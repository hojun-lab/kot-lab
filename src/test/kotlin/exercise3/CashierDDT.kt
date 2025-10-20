package exercise3

import com.ubertob.pesticide.core.DDT
import com.ubertob.pesticide.core.DomainDrivenTest

class CashierDDT: DomainDrivenTest<CashierActions>(allActions) {
    val alice by NamedActor(::CustomerActor)

    @DDT
    fun `customer can buy an item`() = ddtScenario {
        val prices = mapOf(Item.carrot to 2.0, Item.milk to 5.0)
        setUp {
            setUpPrice(prices)
        }.thenPlay(
            alice.  `can add #qty #item`(3, Item.carrot),
            alice.`can add #qty #item`(1, Item.milk),
            alice.`check total is #total`(11.0)
        )
    }
}