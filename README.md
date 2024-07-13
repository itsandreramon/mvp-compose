```kotlin
sealed interface CounterEvent : Event {
  data object Increment : CounterEvent
  data object Decrement : CounterEvent
}

data class CounterState(
  val count: Int,
  override val eventSink: (CounterEvent) -> Unit,
) : State<CounterEvent>

class CounterPresenter : Presenter<CounterState> {

  @Composable
  override fun present(): CounterState {
    var count by rememberSaveable { mutableIntStateOf(0) }

    return CounterState(
      count = count,
      eventSink = { event ->
        when (event) {
          Increment -> count += 1
          Decrement -> count -= 1
        }
      }
    )
  }
}
```

# Screenshots
<img src="https://github.com/user-attachments/assets/ab27a61a-4aee-43bd-aaa7-50d7d9f4a1a6" width=300>
<img src="https://github.com/user-attachments/assets/4673376a-dea0-4df1-a90a-0ffa6bded345" width=300>
