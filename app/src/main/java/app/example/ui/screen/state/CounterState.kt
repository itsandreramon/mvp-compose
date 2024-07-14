package app.example.ui.screen.state

import app.example.ui.base.State
import app.example.ui.screen.event.CounterEvent

data class CounterState(
  val count: Long,
  val message: String,
  val timer: Long,
) : State<CounterEvent>
