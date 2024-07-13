package app.next.ui.screen.example.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.next.ui.base.Presenter
import app.next.ui.screen.example.state.CounterState
import app.next.ui.screen.example.event.CounterEvent
import app.next.ui.screen.example.event.CounterEvent.Decrement
import app.next.ui.screen.example.event.CounterEvent.Increment

class CounterPresenter : Presenter<CounterEvent, CounterState> {

    @Composable
    override fun present(): CounterState {
        var count by remember { mutableIntStateOf(0) }

        return CounterState(
            count = count,
            sink = { event ->
                when (event) {
                    Increment -> count += 1
                    Decrement -> count -= 1
                }
            }
        )
    }
}