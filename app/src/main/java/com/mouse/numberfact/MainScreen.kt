package com.mouse.numberfact

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("") }

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
            placeholder = { Text(text = "Enter number") },
            textStyle = TextStyle.Default.copy(color = Color.White)
        )

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Get fact")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Get fact about random number")
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