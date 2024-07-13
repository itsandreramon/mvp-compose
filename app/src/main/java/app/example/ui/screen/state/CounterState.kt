package app.example.ui.screen.state

import app.example.ui.base.State
import app.example.ui.screen.event.CounterEvent

data class CounterState(
  val count: Int = 0,
  val message: String = "",
) : State<CounterEvent>
