package com.mouse.numberfact.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mouse.numberfact.MainViewModel
import com.mouse.numberfact.data.NumberFact
import com.mouse.numberfact.domain.Void
import com.mouse.numberfact.domain.collectState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    onNavigateToDetail: (NumberFact) -> Unit = {},
) {
    val mainViewModel: MainViewModel = viewModel()
    var inputNumber by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val numberFactFlow by mainViewModel.getNumberFact
    val randomFactFlow by mainViewModel.getRandomFact
    val factHistory by mainViewModel.factHistory.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FactTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = inputNumber,
            onValueChange = {
                error = ""
                inputNumber = it
            },
            label = { Text(text = "Enter number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                mainViewModel.getNumberFact(inputNumber)
            }),
            error = error
        )

        Spacer(modifier = Modifier.size(10.dp))

        FactButton(
            modifier = Modifier.padding(horizontal = 60.dp),
            onClick = {
                keyboardController?.hide()
                mainViewModel.getNumberFact(inputNumber)
            },
            isLoading = isLoading
        ) {
            Text(text = "Get fact", textAlign = TextAlign.Center)
        }
        FactButton(
            modifier = Modifier.padding(horizontal = 60.dp),
            onClick = { mainViewModel.getRandomFact(Void) },
            isLoading = isLoading
        ) {
            Text(text = "Get fact about random number", textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Recently searched")
        Spacer(modifier = Modifier.size(10.dp))

        LazyColumn {
            itemsIndexed(factHistory) { index, fact ->
                FactPreview(fact, onNavigateToDetail)
                if (index < factHistory.lastIndex) {
                    Spacer(modifier = Modifier.size(3.dp))
                    Divider()
                }
            }
        }

        LaunchedEffect("collect_number_fact") {
            numberFactFlow.collectState(
                onLoading = { isLoading = it },
                onError = { error = it },
                onSuccess = onNavigateToDetail
            )
        }
        LaunchedEffect("collect_random_fact") {
            randomFactFlow.collectState(
                onLoading = { isLoading = it },
                onError = { error = it },
                onSuccess = onNavigateToDetail
            )
        }
    }
}