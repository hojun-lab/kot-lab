package domain.tooling

import java.lang.StringBuilder
import kotlin.random.Random

const val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
const val lowercase = "abcdefghijklmnopqrstuvwxyz"
const val digits = "0123456789"

fun stringGenerator(charString: String, minLen: Int, maxLen: Int): Sequence<String> =
    generateSequence {
        randomString(charString, minLen, maxLen)
    }

fun randomString(charString: String, minLen: Int, maxLen: Int) =
    StringBuilder().run() {
        val len = if (maxLen > minLen) Random.nextInt(maxLen - minLen) else minLen
        repeat(len) {
            append(charString.random())
        }
        toString()
    }

fun substituteRandomChar(fromCharSet: String, intoString: String): String =
    intoString
        .toCharArray()
        .apply { set(Random.nextInt(intoString.length), fromCharSet.random()) }
        .joinToString(separator = "")