package app.example.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
  val increment = remember {
    { state.eventSink(CounterEvent.Increment) }
  }

  val decrement = remember {
    { state.eventSink(CounterEvent.Decrement) }
  }

  Scaffold(content = { paddingValues ->
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        TextButton(onClick = increment) {
          Text("Increment")
        }
        Text("Count: ${state.count}")
        TextButton(onClick = decrement) {
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
      eventSink = {}
    )
  )
}
