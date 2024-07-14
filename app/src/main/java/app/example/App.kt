package app.example

import android.app.Application
import app.example.ui.base.PresenterManager

class App : Application() {

  lateinit var presenterManager: PresenterManager

  override fun onCreate() {
    super.onCreate()
    presenterManager = PresenterManager(this)
  }
}
