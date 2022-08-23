package com.mouse.numberfact.domain.interaction

import com.mouse.numberfact.NumberFactApp
import com.mouse.numberfact.domain.NumberApi
import com.mouse.numberfact.domain.Void
import kotlinx.coroutines.CoroutineScope

class GetRandomFactInteraction(
    private val numberApi: NumberApi = NumberFactApp.instance.numberApi,
    scope: CoroutineScope,
) : Interaction.Base<Void, String>(scope) {

    override suspend fun process(params: Void): String {
        return numberApi.getRandomNumber()
    }
}