package com.mouse.numberfact.data

import android.os.Parcelable
import com.mouse.numberfact.extractNumber
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumberFact(
    private val raw: String = "",
) : Parcelable {
    @IgnoredOnParcel
    val number: String = raw.extractNumber()

    @IgnoredOnParcel
    val fact: String = raw.replace(number, "").trim()

    override fun toString() = raw
}