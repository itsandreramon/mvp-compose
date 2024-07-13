package app.next.ui.screen

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
import app.next.ui.screen.event.CounterEvent
import app.next.ui.screen.presenter.CounterPresenter
import app.next.ui.screen.state.CounterState

@Composable
fun ExampleScreen(presenter: CounterPresenter) {
    val state = presenter.present()
    ExampleContent(state)
}

@Composable
private fun ExampleContent(state: CounterState) {
    Scaffold(content = { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextButton(onClick = {
                    state.eventSink(CounterEvent.Increment)
                }) {
                    Text("Increment")
                }
                Text("Count: ${state.count}")
                TextButton(onClick = {
                    state.eventSink(CounterEvent.Decrement)
                }) {
                    Text("Decrement")
                }
            }
        }
    })
}