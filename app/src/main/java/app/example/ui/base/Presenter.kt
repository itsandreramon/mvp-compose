package app.example.ui.base

import app.example.ui.screen.event.CounterEvent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class Presenter<S : State<out Event>> {

  protected val observables = CompositeDisposable()

  protected val eventSink = BehaviorSubject.create<CounterEvent>()

  abstract val state: Observable<S>

  fun onEvent(event: CounterEvent) {
    eventSink.onNext(event)
  }

  fun clear() {
    observables.clear()
  }
}
