package com.mouse.numberfact.domain

import android.content.res.Resources
import androidx.core.text.isDigitsOnly
import com.mouse.numberfact.NumberFactApp
import com.mouse.numberfact.R

class NumberValidate(
    private val resources: Resources = NumberFactApp.instance.resources,
) : Validation<String> {

    override fun validate(value: String): Validation.Result {
        val errors = mutableListOf<String>()
        when {
            value.isBlank() -> errors += resources.getString(R.string.input_your_number_please)
            !value.isDigitsOnly() -> errors += resources.getString(R.string.you_should_input_number)
        }
        return Validation.Result(errors)
    }
}