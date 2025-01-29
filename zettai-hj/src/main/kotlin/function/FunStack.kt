package com.rojojun.function

data class FunStack<T>(private val data: List<T> = emptyList()) {
    fun push(element: T) = FunStack(listOf(element) + data)
    fun pop(): Pair<T, FunStack<T>> = data.first() to FunStack(data.drop(1))
    fun size(): Int = data.size
}