package com.rojojun.function

typealias FUN<A, B> = (A) -> B;
infix fun <A, B, C> FUN<A, B>.andThen(other: FUN<B, C>): FUN<A, C> =
    { a: A -> other(this(a)) }

fun <U: Any> CharSequence?.unlessNullOrEmpty(f: (CharSequence) -> U): U? =
    if (isNullOrBlank()) null else f(this)

fun <T> T.printIt(prefix: String = ">"): T = also { println("$prefix: $this") }