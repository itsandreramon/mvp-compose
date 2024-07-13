package app.next.ui.screen.state

import app.next.ui.base.State
import app.next.ui.screen.event.CounterEvent

data class CounterState(
    val count: Int,
    override val eventSink: (CounterEvent) -> Unit,
) : State<CounterEvent>