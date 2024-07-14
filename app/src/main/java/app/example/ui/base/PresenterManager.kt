package app.example.ui.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class PresenterManager(application: Application) {

  private val presentersMap = ConcurrentHashMap<KClass<out Presenter<*>>, Presenter<*>>()

  init {
    application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
      override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // not implemented
      }

      override fun onActivityStarted(activity: Activity) {
        // not implemented
      }

      override fun onActivityResumed(activity: Activity) {
        // not implemented
      }

      override fun onActivityPaused(activity: Activity) {
        // not implemented
      }

      override fun onActivityStopped(activity: Activity) {
        // not implemented
      }

      override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // not implemented
      }

      override fun onActivityDestroyed(activity: Activity) {
        if (!activity.isChangingConfigurations) {
          presentersMap.clear()
        }
      }
    })
  }

  @Suppress("UNCHECKED_CAST")
  fun <P : Presenter<*>> getPresenter(key: KClass<out Presenter<*>>, factory: PresenterFactory<P>): P {
    return presentersMap.getOrPut(key) { factory.create() } as P
  }
}
