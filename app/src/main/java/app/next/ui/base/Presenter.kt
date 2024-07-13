package app.next.ui.base

import androidx.compose.runtime.Composable

interface Presenter<E : Event, T : State<E>> {

    @Composable
    fun present(): T
}