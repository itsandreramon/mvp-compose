```kotlin
sealed interface CounterEvent : Event {
  data object Increment : CounterEvent
  data object Decrement : CounterEvent
}

data class CounterState(
  val count: Int,
) : State<CounterEvent>

class CounterPresenter : Presenter<CounterState>() {

  private val eventSink = PublishSubject.create<CounterEvent>()

  private val count = eventSink.scan(0L) { count, event ->
    when (event) {
      CounterEvent.Increment -> count + 1
      CounterEvent.Decrement -> count - 1
    }
  }

  override val stateObservable = count
    .map { count -> CounterState(count) }
    .replay(1)
    .distinctUntilChanged()

  fun onEvent(event: CounterEvent) {
    eventSink.onNext(event)
  }
}

@Composable
fun CounterScreen(presenter: CounterPresenter) {
  val state = presenter.stateObservable.subscribeAsStateWithLifecycle()
  state?.let { CounterContent(it, presenter::onEvent) }
}
```

# Screenshots
<img src="https://github.com/user-attachments/assets/ab27a61a-4aee-43bd-aaa7-50d7d9f4a1a6" width=300>
<img src="https://github.com/user-attachments/assets/4673376a-dea0-4df1-a90a-0ffa6bded345" width=300>
