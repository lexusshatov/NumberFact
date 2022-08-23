package com.mouse.numberfact.domain.interaction

import com.mouse.numberfact.NumberFactApp
import com.mouse.numberfact.data.NumberFact
import com.mouse.numberfact.data.database.NumberFactDao
import com.mouse.numberfact.data.database.NumberFactEntity
import com.mouse.numberfact.domain.NumberApi
import com.mouse.numberfact.domain.Void
import kotlinx.coroutines.CoroutineScope

class GetRandomFactInteraction(
    private val numberApi: NumberApi = NumberFactApp.instance.numberApi,
    private val dao: NumberFactDao = NumberFactApp.instance.dao,
    scope: CoroutineScope,
) : Interaction.Base<Void, NumberFact>(scope) {

    override suspend fun process(params: Void): NumberFact {
        return NumberFact(numberApi.getRandomNumber()).also {
            val numberFactEntity = NumberFactEntity(raw = it.toString())
            dao.addFact(numberFactEntity)
        }
    }
}