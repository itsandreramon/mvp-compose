package app.example.ui.screen.state

import app.example.ui.base.State
import app.example.ui.screen.event.CounterEvent

data class CounterState(
  val count: Int,
  override val eventSink: (CounterEvent) -> Unit,
) : State<CounterEvent>
