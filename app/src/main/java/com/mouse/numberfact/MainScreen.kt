package com.mouse.numberfact

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen() {
    val mainViewModel: MainViewModel = viewModel()
    var text by remember { mutableStateOf("") }
    val isLoading by mainViewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text(text = "Enter number") },
            textStyle = TextStyle.Default.copy(color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { mainViewModel.load() })
        )

        Spacer(modifier = Modifier.size(10.dp))

        FactButton(
            modifier = Modifier.padding(horizontal = 60.dp),
            onClick = { mainViewModel.load() },
            isLoading = isLoading
        ) {
            Text(text = "Get fact", textAlign = TextAlign.Center)
        }
        FactButton(
            modifier = Modifier.padding(horizontal = 60.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Get fact about random number", textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.size(20.dp))

        LazyColumn {
            items(100) {
                FactPreview(text = (0..100).random().toString())
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