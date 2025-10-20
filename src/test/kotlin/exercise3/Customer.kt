package exercise3

class Customer {
    private val prices = mutableMapOf<Item, Double>()
    private val customerTotal = mutableMapOf<String, Double>()

    fun putAll(offer: Map<Item, Double>) {
        prices.putAll(offer)
        customerTotal.clear()
    }
}
