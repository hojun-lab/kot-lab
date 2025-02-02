package com.rojojun

data class ListName(
    val name: String
) {
    companion object {
        fun fromTrusted(name: String): ListName = ListName(name)
        fun fromUntrusted(name: String): ListName? =
            if (name.matches(pathElementPattern) && name.length in 4..40) fromTrusted(name) else null
        fun fromUntrustedOrThrow(name: String) : ListName =
            fromUntrusted(name) ?: throw IllegalArgumentException("$name not found")
    }
}
