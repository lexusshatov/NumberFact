package com.mouse.numberfact

fun String.extractNumber(): String {
    return Regex("""[+-]*[0-9]([0-9.,e^+-]*[0-9])?""")
        .find(this)
        ?.value
        ?: "undefined"
}