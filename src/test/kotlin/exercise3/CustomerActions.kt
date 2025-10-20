package exercise3

import com.ubertob.pesticide.core.DdtActions
import com.ubertob.pesticide.core.DdtProtocol
import com.ubertob.pesticide.core.DomainSetUp

enum class Item {
    milk,
    carrot,
    ;
}

interface CashierActions: DdtActions<DdtProtocol> {
    fun setUpPrice(prices: Map<Item, Double>)
    fun totalFor(actorName: String): Double
    fun addItem(actorName: String, qty: Int, item: Item)
}

val allActions = setOf(DomainActions)

class DomainActions : CashierActions {
    val cashier = Customer()

    override fun setUpPrice(prices: Map<Item, Double>) {
        cashier.putAll(prices)
    }

    override fun totalFor(actorName: String): Double {
        TODO("Not yet implemented")
    }

    override fun addItem(actorName: String, qty: Int, item: Item) {
        TODO("Not yet implemented")
    }

    override val protocol: DdtProtocol
        get() = TODO("Not yet implemented")

    override fun prepare(): DomainSetUp {
        TODO("Not yet implemented")
    }

}