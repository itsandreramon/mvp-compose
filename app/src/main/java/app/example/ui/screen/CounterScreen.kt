package app.example.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.example.ui.screen.event.CounterEvent
import app.example.ui.screen.presenter.CounterPresenter
import app.example.ui.screen.state.CounterState

@Composable
fun CounterScreen(presenter: CounterPresenter) {
  val state = presenter.present()
  CounterContent(state)
}

@Composable
private fun CounterContent(state: CounterState) {
  val eventSink = state.eventSink

  Scaffold(content = { paddingValues ->
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        TextButton(onClick = { eventSink(CounterEvent.Increment)} ) {
          Text("Increment")
        }
        Text("Count: ${state.count}")
        Text(state.message)
        TextButton(onClick = { eventSink(CounterEvent.Decrement)}) {
          Text("Decrement")
        }
      }
    }
  })
}

@Preview
@Composable
private fun CounterScreenContentPreview() {
  CounterContent(
    state = CounterState(
      count = 0,
      message = "Count is 0",
      eventSink = {}
    )
  )
}
