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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { println("SEARCH") })
        )

        Spacer(modifier = Modifier.size(10.dp))

        FactButton(
            onClick = { /*TODO*/ },
            isLoading = isLoading
        ) {
            Text(text = "Get fact")
        }
        FactButton(onClick = { /*TODO*/ }) {
            Text(text = "Get fact about random number")
        }

        Spacer(modifier = Modifier.size(20.dp))

        LazyColumn {
            items(100) {
                FactPreview(text = (0..100).random().toString())
            }
        }

        LaunchedEffect(key1 = "Test") {
            delay(3000)
            isLoading = false
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