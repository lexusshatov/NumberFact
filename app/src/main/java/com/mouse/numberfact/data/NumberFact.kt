package com.mouse.numberfact.data

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumberFact(
    private val raw: String = "",
) : Parcelable {
    @IgnoredOnParcel
    val number: String = raw.takeWhile { it.isDigit() }

    @IgnoredOnParcel
    val fact: String = raw.dropWhile { it.isDigit() }.trim()

    override fun toString() = raw
}