package app.example.ui.base

interface State<T : Event> {
    val eventSink: (T) -> Unit
}