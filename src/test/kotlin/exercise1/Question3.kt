package exercise1

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.random.Random
import kotlin.test.Test

class Question3 {
    fun random(): Int = Random.nextInt(
        from = 1,
        until =  100_000_000,
    )

    @Test
    fun `더하기`() {
        repeat(100) {
            val x = random()
            val y = Random.nextInt(from = 1, until = 100)

            val ones = List(y) { 1 }

            val z = ones.fold(x) {
                acc, one -> acc + one
            }

            expectThat(z).isEqualTo(x + y)
        }
    }

    @Test
    fun `함수형 더하기`() {
        val testCase: (Int) -> Unit = { _: Int ->
            val x = random()
            val y = Random.nextInt(from = 1, until = 100)
            val ones = List(y) { 1 }
                .fold(x) { acc, one -> acc + one }

            expectThat(ones).isEqualTo(x + y)
        }

        (1..100).forEach(testCase)
    }
}