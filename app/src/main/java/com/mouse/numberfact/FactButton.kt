package com.mouse.numberfact

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FactButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    content: @Composable (RowScope.() -> Unit),
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 3.dp
            )
        } else content(this)
    }
}