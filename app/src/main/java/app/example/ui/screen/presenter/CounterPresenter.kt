package app.example.ui.screen.presenter

import app.example.ui.base.Presenter
import app.example.ui.screen.event.CounterEvent
import app.example.ui.screen.state.CounterState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CounterPresenter : Presenter<CounterState>() {

  private val count = BehaviorSubject.createDefault(0)

  private val message = count.map { count ->
    when {
      count < 0 -> "Counter is less than 0"
      count > 0 -> "Counter is greater than 0"
      else -> "Counter is 0"
    }
  }

  override val uiState = Observable.combineLatest(count, message) { count, message ->
    CounterState(count, message)
  }.distinctUntilChanged()

  override fun present() {
    observables.add(
      eventSink.subscribe { event ->
        when (event) {
          CounterEvent.Increment -> count.onNext(count.value!! + 1)
          CounterEvent.Decrement -> count.onNext(count.value!! - 1)
        }
      }
    )
  }
}
