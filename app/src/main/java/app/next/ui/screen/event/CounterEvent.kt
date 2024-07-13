package app.next.ui.screen.event

import app.next.ui.base.Event

sealed interface CounterEvent : Event {
    data object Increment : CounterEvent
    data object Decrement : CounterEvent
}