//package exercise1
//
class CashRegister(
    val products: Map<String, Double>,
    val promotion: Map<String, String>
) {
    fun checkout() {

    }
}
//    fun checkout(items: List<String>): Double {
//        return items.groupingBy { it }
//            .eachCount()
//            .entries
//            .sumOf { (product, count) ->
//                {
//                    //  m * n -> m = 수량, n = 가격 결정
//                    //  count 가 m의 배수라면 n * (count / m)
//
//                    val price = products.getValue(product)
//                    val promotion = promotion.getValue(product)
//                    val result = count % promotion.split("x")[0].toInt()
//
//                    if (result == 0) {
//                        promotion.split("x")[1].toInt()
//                        val priceQuantity = promotion.split("x")[1].toInt() * (count / promotion.split("x")[0].toInt())
//                        priceQuantity * price
//                    }
//                }
//            }
//    }
//}
//
class Question2 {
// TODO 6월 29일까지 구현
}