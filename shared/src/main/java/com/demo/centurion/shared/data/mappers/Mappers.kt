
package com.demo.centurion.shared.data.mappers

import com.demo.centurion.shared.data.models.CatsResponse
import com.demo.centurion.shared.presentation.states.UIModel

fun catResponseToUIModel(cats: List<CatsResponse>): List<UIModel> {
  return cats.map {
    UIModel(it.id)
  }
}

fun dogResponseToUIModel(dogs: List<String>): List<UIModel> {
  return dogs.map {
    UIModel(it)
  }
}