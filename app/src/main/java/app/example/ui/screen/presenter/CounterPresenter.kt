package app.example.ui.screen.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import app.example.ui.base.Presenter
import app.example.ui.screen.event.CounterEvent.Decrement
import app.example.ui.screen.event.CounterEvent.Increment
import app.example.ui.screen.state.CounterState

class CounterPresenter : Presenter<CounterState> {

  @Composable
  override fun present(): CounterState {
    var count by rememberSaveable { mutableIntStateOf(0) }

    return CounterState(
      count = count,
      eventSink = { event ->
        when (event) {
          Increment -> count += 1
          Decrement -> count -= 1
        }
      }
    )
  }
}
