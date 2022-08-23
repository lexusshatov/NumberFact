package com.mouse.numberfact.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mouse.numberfact.theme.NumberFactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberFactTheme {
                NumberFactNavHost()
            }
        }
    }
}