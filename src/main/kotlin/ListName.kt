package rojojun

data class ListName(val name: String) {
    companion object {
        fun fromUntrustedOrThrow(name: String): ListName =
            fromUntrusted(name) ?: throw IllegalArgumentException("Invalid list name $name")

        val pathElementPattern = Regex(pattern = "[A-Za-z0-9-]+")

        fun fromUntrusted(name: String): ListName? =
            if (name.matches(pathElementPattern) && name.length in 1..40) fromTrusted(name) else null

        fun fromTrusted(name: String): ListName = ListName(name)
    }
}
