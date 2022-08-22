package com.mouse.numberfact.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mouse.numberfact.domain.State

@Composable
fun <T : Any> FactButton(
    modifier: Modifier = Modifier,
    state: State<T> = State.Idle,
    onClick: () -> Unit,
    content: @Composable (RowScope.() -> Unit),
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = state !is State.Loading
    ) {
        if (state is State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else content(this)
    }
}