import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.random.Random

class AdditionTest {
    fun randomNatural() = Random.nextInt(from = 1, until = 100_000_000)

    @Test
    fun `1씩 더해가는 테스트`() {
        repeat(100) {
            val x = randomNatural()
            val y = Random.nextInt(1, 100)
            val ones = List(y) { 1 }
            val z = ones.fold(x) { acc, one -> acc + one }
            expectThat(z).isEqualTo(x + y)
        }
    }
}