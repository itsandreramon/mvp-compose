package app.next.ui.base

interface State<T : Event> {
    val eventSink: (T) -> Unit
}