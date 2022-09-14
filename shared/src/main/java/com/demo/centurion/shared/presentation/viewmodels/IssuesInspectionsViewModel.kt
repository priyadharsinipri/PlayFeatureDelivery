
package com.demo.centurion.shared.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.centurion.shared.data.network.NetworkResult
import com.demo.centurion.shared.data.repository.InspectionsRepository
import com.demo.centurion.shared.data.repository.IssuesRepository
import com.demo.centurion.shared.presentation.states.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatsDogViewModel(
    private val issuesRepository: IssuesRepository,
    private val inspectionsRepository: InspectionsRepository
) : ViewModel() {

  private val _cats: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
  val cats = _cats.asStateFlow()

  private val _dogs: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
  val dogs = _dogs.asStateFlow()

  fun getDogs() {
    viewModelScope.launch {
      when (val value = issuesRepository.getIssues()) {
        is NetworkResult.Success -> _dogs.value = UIState.ShowData(value.data)
        is NetworkResult.APIError -> _dogs.value = UIState.Error("An API occurred")
        is NetworkResult.ServerError -> _dogs.value = UIState.Error("An error occurred")
        is NetworkResult.NetworkError -> _dogs.value = UIState.Error("You don't have an active internet connection")
      }
    }
  }

  fun getCats() {
    viewModelScope.launch {
      when (val value = inspectionsRepository.getCats()) {
        is NetworkResult.Success -> _cats.value = UIState.ShowData(value.data)
        is NetworkResult.APIError -> _cats.value = UIState.Error("An API occurred")
        is NetworkResult.ServerError -> _cats.value = UIState.Error("An error occurred")
        is NetworkResult.NetworkError -> _cats.value = UIState.Error("You don't have an active internet connection")
      }
    }
  }

}