package com.mouse.numberfact.domain

import com.mouse.numberfact.NumberFactApp
import kotlinx.coroutines.CoroutineScope

class GetNumberFactInteraction(
    private val numberApi: NumberApi = NumberFactApp.instance.numberApi,
    scope: CoroutineScope,
) : Interaction.Base<String, String>(scope) {

    override suspend fun process(params: String): String {
        return numberApi.getNumberFact(params)
    }
}