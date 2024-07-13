package app.next.ui.base

interface State<T : Event> {
    val sink: (T) -> Unit
}