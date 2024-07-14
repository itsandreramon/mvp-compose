package app.example.ui.base

interface PresenterFactory <P : Presenter<*>> {
  fun create(): P
}
