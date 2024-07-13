package app.example.ui.base

import androidx.compose.runtime.Composable

interface Presenter<S : State<out Event>> {

  @Composable
  fun present(): S
}
