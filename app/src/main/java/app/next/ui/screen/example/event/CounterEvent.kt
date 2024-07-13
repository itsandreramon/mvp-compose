package app.next.ui.screen.example.event

import app.next.ui.base.Event

sealed interface CounterEvent : Event {
    data object Increment : CounterEvent
    data object Decrement : CounterEvent
}