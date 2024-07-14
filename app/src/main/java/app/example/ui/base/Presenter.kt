package app.example.ui.base

import app.example.ui.screen.event.CounterEvent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

abstract class Presenter<S : State<out Event>> {

  protected val subscriptions = CompositeDisposable()

  protected val eventSink = PublishSubject.create<CounterEvent>()

  abstract val mStateObservable: Observable<S>

  val stateObservable by lazy {
    mStateObservable.replay(1)
      .refCount(1, 5, TimeUnit.SECONDS)
      .distinctUntilChanged()
  }

  fun onEvent(event: CounterEvent) {
    eventSink.onNext(event)
  }

  fun destroy() {
    subscriptions.clear()
  }
}
