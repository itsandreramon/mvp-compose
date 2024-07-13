package app.example.ui.screen.event

import app.example.ui.base.Event

sealed interface CounterEvent : Event {
    data object Increment : CounterEvent
    data object Decrement : CounterEvent
}