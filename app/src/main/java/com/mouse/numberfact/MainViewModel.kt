package com.mouse.numberfact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mouse.numberfact.domain.GetNumberFactInteraction
import com.mouse.numberfact.domain.NumberApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val numberApi: NumberApi = NumberFactApp.instance.numberApi,
) : ViewModel() {

    val getNumberFact = GetNumberFactInteraction(scope = viewModelScope)

    private val _isLoadingRandomNumber: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingRandomNumber: StateFlow<Boolean> = _isLoadingRandomNumber

    fun loadRandomNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoadingRandomNumber.value = true
            val result = numberApi.getRandomNumber()
            println(result)
            _isLoadingRandomNumber.value = false
        }
    }
}