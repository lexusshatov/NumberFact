package com.mouse.numberfact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mouse.numberfact.domain.interaction.GetNumberFactInteraction
import com.mouse.numberfact.domain.interaction.GetRandomFactInteraction

class MainViewModel : ViewModel() {

    val getNumberFact = GetNumberFactInteraction(scope = viewModelScope)
    val getRandomFact = GetRandomFactInteraction(scope = viewModelScope)
}