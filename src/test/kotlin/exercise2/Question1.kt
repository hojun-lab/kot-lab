package exercise2

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class Question1 {
    @Test
    fun `push into the stack`() {
        val stack1 = FunStack<Char>()
        val stack2 = stack1.push('A')
        expectThat(stack1.size()).isEqualTo(0)
        expectThat(stack2.size()).isEqualTo(1)
    }

    @Test
    fun `push push pop`() {
        val (b, stack) = FunStack<Char>()
            .push('A')
            .push('B')
            .pop()

        expectThat(stack.size()).isEqualTo(1)
        expectThat(b).isEqualTo('B')
    }
}

data class FunStack<T>(
    private val elements: List<T> = emptyList(),
) {
    fun push(element: T): FunStack<T> = FunStack(listOf(element) + elements)

    fun pop(): Pair<T, FunStack<T>> = elements.first() to FunStack(elements.drop(1))

    fun size() = elements.size
}
