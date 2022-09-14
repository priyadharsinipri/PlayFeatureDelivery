
package com.demo.centurion.shared.presentation.states

/**
 * Holds the Various States for UI
 */
sealed class UIState {
  object Loading : UIState()
  data class ShowData<T>(val data: T) : UIState()
  data class Error(val message: String) : UIState()
}