package com.mouse.numberfact.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mouse.numberfact.MainViewModel
import com.mouse.numberfact.domain.State

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    onNavigateToDetail: (String) -> Unit = {},
) {
    val mainViewModel: MainViewModel = viewModel()
    var inputNumber by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val numberFactFlow by mainViewModel.getNumberFact

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
            textStyle = TextStyle.Default.copy(color = Color.White),
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
            onClick = { mainViewModel.loadRandomNumber() },
            isLoading = isLoading
        ) {
            Text(text = "Get fact about random number", textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.size(20.dp))

        LazyColumn {
            items(100) {
                FactPreview(text = (0..100).random().toString())
            }
        }

        LaunchedEffect("collect") {
            numberFactFlow.collect { state ->
                when (state) {
                    is State.Error -> {
                        isLoading = false
                        error = state.message
                    }
                    State.Idle -> isLoading = false
                    State.Loading -> isLoading = true
                    is State.Success -> onNavigateToDetail(state.result)
                }
            }
        }
    }
}

@Composable
fun FactPreview(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text
    )
}