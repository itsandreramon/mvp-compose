package app.example.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import app.example.App
import app.example.ui.screen.CounterScreen
import app.example.ui.screen.presenter.CounterPresenter
import app.example.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent { App(application as App) }
  }
}

@Composable
private fun App(app: App) {
  AppTheme {
    CounterScreen(presenter = remember {
      app.presenterManager.getPresenter(
        key = CounterPresenter::class,
        factory = { CounterPresenter() }
      )
    })
  }
}
