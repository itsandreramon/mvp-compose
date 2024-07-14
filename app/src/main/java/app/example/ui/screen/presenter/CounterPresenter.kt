package app.example.ui.screen.presenter

import app.example.ui.base.Presenter
import app.example.ui.screen.event.CounterEvent
import app.example.ui.screen.state.CounterState
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class CounterPresenter : Presenter<CounterState>() {

  private val timer = Observable
    .interval(1, TimeUnit.SECONDS)
    .startWithItem(0)

  private val count = eventSink.scan(0L) { count, event ->
    when (event) {
      CounterEvent.Increment -> count + 1
      CounterEvent.Decrement -> count - 1
    }
  }

  private val message = count.map { count ->
    when {
      count < 0 -> "Counter is less than 0"
      count > 0 -> "Counter is greater than 0"
      else -> "Counter is 0"
    }
  }

  override val mStateObservable = Observable
    .combineLatest(count, message, timer) { count, message, timer ->
      CounterState(count, message, timer)
    }
}
