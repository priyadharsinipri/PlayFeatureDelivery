
package com.demo.centurion.shared.data.repository

import android.util.Log
import com.demo.centurion.shared.data.mappers.catResponseToUIModel
import com.demo.centurion.shared.data.network.CatsAPI
import com.demo.centurion.shared.data.network.NetworkResult
import com.demo.centurion.shared.presentation.states.UIModel
import java.io.IOException

interface CatsRepository {
  suspend fun getCats(): NetworkResult<List<UIModel>>
}

class CatsRepositoryImpl(
    private val catsAPI: CatsAPI
) : CatsRepository {
  override suspend fun getCats(): NetworkResult<List<UIModel>> =
      try {
        val response = catsAPI.getCats()
        when {
          response.isSuccessful -> NetworkResult.Success(catResponseToUIModel(response.body()!!))
          else -> NetworkResult.APIError
        }

      } catch (e: IOException) {
        Log.d("Error", e.message.toString())
        NetworkResult.NetworkError
      } catch (e: Exception) {
        Log.d("Error", e.message.toString())
        NetworkResult.ServerError()
      }

}