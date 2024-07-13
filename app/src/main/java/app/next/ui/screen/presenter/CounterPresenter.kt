package app.next.ui.screen.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.next.ui.base.Presenter
import app.next.ui.screen.event.CounterEvent
import app.next.ui.screen.event.CounterEvent.Decrement
import app.next.ui.screen.event.CounterEvent.Increment
import app.next.ui.screen.state.CounterState

class CounterPresenter : Presenter<CounterEvent, CounterState> {

    @Composable
    override fun present(): CounterState {
        var count by remember { mutableIntStateOf(0) }

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