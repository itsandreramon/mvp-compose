package app.next.ui.screen.example.state

import app.next.ui.base.State
import app.next.ui.screen.example.event.CounterEvent

data class CounterState(
    val count: Int,
    override val sink: (CounterEvent) -> Unit,
) : State<CounterEvent>