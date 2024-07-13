package app.example.ui.screen.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
    val message by remember {
      derivedStateOf {
        when {
          count < 0 -> "Counter is less than 0"
          count > 0 -> "Counter is greater than 0"
          else -> "Counter is 0"
        }
      }
    }

    return CounterState(
      count = count,
      message = message,
      eventSink = { event ->
        when (event) {
          Increment -> count += 1
          Decrement -> count -= 1
        }
      }
    )
  }
}
