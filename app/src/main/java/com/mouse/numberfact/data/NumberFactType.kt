package com.mouse.numberfact.data

import android.os.Bundle
import androidx.navigation.NavType

class NumberFactType : NavType<NumberFact>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): NumberFact? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): NumberFact {
        return NumberFact(value)
    }

    override fun put(bundle: Bundle, key: String, value: NumberFact) {
        bundle.putParcelable(key, value)
    }
}