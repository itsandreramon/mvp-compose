package app.example.ui.screen.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

@Composable
fun <T : Any> Observable<T>.subscribeAsStateWithLifecycle(
  initialValue: T? = null,
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): State<T?> {
  return subscribeAsStateWithLifecycle(
    initialValue = initialValue,
    lifecycle = lifecycleOwner.lifecycle,
    minActiveState = minActiveState,
  )
}

@Composable
fun <T : Any> Observable<T>.subscribeAsStateWithLifecycle(
  initialValue: T?,
  lifecycle: Lifecycle,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): State<T?> {
  val state = remember { mutableStateOf(initialValue) }

  DisposableEffect(lifecycle, this) {
    var disposable: Disposable? = null

    val lifecycleObserver = object : DefaultLifecycleObserver {
      override fun onStart(owner: LifecycleOwner) {
        if (lifecycle.currentState.isAtLeast(minActiveState)) {
          disposable = observeOn(AndroidSchedulers.mainThread())
            .subscribe { value -> state.value = value }
        }
      }

      override fun onStop(owner: LifecycleOwner) {
        disposable?.dispose()
        disposable = null
      }
    }

    lifecycle.addObserver(lifecycleObserver)

    onDispose {
      lifecycle.removeObserver(lifecycleObserver)
      disposable?.dispose()
    }
  }

  return state
}
