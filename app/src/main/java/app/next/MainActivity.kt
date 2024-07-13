package app.next

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import app.next.ui.screen.example.ExampleScreen
import app.next.ui.screen.example.presenter.CounterPresenter
import app.next.ui.theme.NextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { NextApp() }
    }
}

@Composable
private fun NextApp() {
    NextTheme {
        ExampleScreen(presenter = remember {
            CounterPresenter()
        })
    }
}