package com.mouse.numberfact.domain.interaction

import com.mouse.numberfact.NumberFactApp
import com.mouse.numberfact.data.NumberFact
import com.mouse.numberfact.data.database.NumberFactDao
import com.mouse.numberfact.data.database.NumberFactEntity
import com.mouse.numberfact.domain.NumberApi
import com.mouse.numberfact.domain.validate.NumberValidate
import kotlinx.coroutines.CoroutineScope

class GetNumberFactInteraction(
    private val numberApi: NumberApi = NumberFactApp.instance.numberApi,
    private val dao: NumberFactDao = NumberFactApp.instance.dao,
    scope: CoroutineScope,
    numberValidate: NumberValidate = NumberValidate()
) : Interaction.Base<String, NumberFact>(scope, validation = numberValidate) {

    override suspend fun process(params: String): NumberFact {
        return NumberFact(numberApi.getNumberFact(params)).also {
            val numberFactEntity = NumberFactEntity(raw = it.toString())
            dao.addFact(numberFactEntity)
        }
    }
}