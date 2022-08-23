package com.mouse.numberfact.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mouse.numberfact.data.NumberFact

@Composable
fun FactPreview(
    numberFact: NumberFact,
    onNavigateToDetail: (NumberFact) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToDetail(numberFact) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight(),
            text = numberFact.number,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            fontSize = 22.sp,
            maxLines = 1
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            modifier = Modifier.weight(1F),
            text = numberFact.fact
        )
    }
}