package app.example.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.example.ui.screen.event.CounterEvent
import app.example.ui.screen.presenter.CounterPresenter
import app.example.ui.screen.state.CounterState

@Composable
fun CounterScreen(presenter: CounterPresenter) {
  val state by presenter.uiState
    .subscribeAsState(initial = CounterState())

  LaunchedEffect(presenter) {
    presenter.present()
  }

  CounterContent(state = state, presenter::onEvent)
}

@Composable
private fun CounterContent(state: CounterState, onEvent: (CounterEvent) -> Unit) {
  Scaffold(content = { paddingValues ->
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        TextButton(onClick = { onEvent(CounterEvent.Increment) }) {
          Text("Increment")
        }
        Text("Count: ${state.count}")
        Text(state.message)
        TextButton(onClick = { onEvent(CounterEvent.Decrement) }) {
          Text("Decrement")
        }
      }
    }
  })
}
