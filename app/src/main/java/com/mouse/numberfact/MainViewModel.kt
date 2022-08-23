package com.mouse.numberfact

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mouse.numberfact.data.NumberFact
import com.mouse.numberfact.data.database.NumberFactDao
import com.mouse.numberfact.domain.interaction.GetNumberFactInteraction
import com.mouse.numberfact.domain.interaction.GetRandomFactInteraction

class MainViewModel(
    dao: NumberFactDao = NumberFactApp.instance.dao,
) : ViewModel() {

    val factHistory: LiveData<List<NumberFact>> =
        Transformations.map(dao.getHistory()) { entities ->
            entities.map { NumberFact(it.raw) }
        }

    val getNumberFact = GetNumberFactInteraction(scope = viewModelScope)
    val getRandomFact = GetRandomFactInteraction(scope = viewModelScope)
}