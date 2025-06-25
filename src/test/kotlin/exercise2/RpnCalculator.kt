package exercise2

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

object RpnCalculator {
    val operationsMap = mapOf<String, (Double, Double) -> Double>(
        "+" to Double::plus,
        "-" to Double::minus,
        "*" to Double::times,
        "/" to Double::div
    )

    val funStack = FunStack<Double>()

    fun calculate(value: String): Double =
        value.split(" ")
            .fold(funStack, ::reduce)
            .pop()
            .first


    private fun reduce(stack: FunStack<Double>, token: String): FunStack<Double> =
        if (operationsMap.containsKey(token)) {
            val (b, tempStack) = stack.pop()
            val (a, newStack) = tempStack.pop()

            newStack.push(operate(token, a, b))
        } else {
            stack.push(token.toDouble())
        }

    private fun operate(token: String, a: Double, b: Double) =
        operationsMap[token]?.invoke(a, b) ?: throw IllegalArgumentException("지원하지 않는 연산자입니다..")
}

class TestCalculator {

    @Test
    fun getResult() {
        val result1 = RpnCalculator.calculate("4 5 +")
        val result2 = RpnCalculator.calculate("6 2 /")
        val result3 = RpnCalculator.calculate("5 6 2 1 + / *")
        val result4 = RpnCalculator.calculate("2 5 * 4 + 3 2 * 1 + /")

        expectThat(result1).isEqualTo(9.0)
        expectThat(result2).isEqualTo(3.0)
        expectThat(result3).isEqualTo(10.0)
        expectThat(result4).isEqualTo(2.0)
    }
}