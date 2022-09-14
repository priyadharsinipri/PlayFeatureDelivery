
package com.demo.centurion.shared.data.repository

import android.util.Log
import com.demo.centurion.shared.data.mappers.dogResponseToUIModel
import com.demo.centurion.shared.data.network.DogsAPI
import com.demo.centurion.shared.data.network.NetworkResult
import com.demo.centurion.shared.presentation.states.UIModel
import java.io.IOException

interface DogsRepository {
  suspend fun getDogs(): NetworkResult<List<UIModel>>
}

class DogsRepositoryImpl(
    private val dogsAPI: DogsAPI
) : DogsRepository {

  override suspend fun getDogs(): NetworkResult<List<UIModel>> =
      try {
        val response = dogsAPI.getDogs()
        when {
          response.isSuccessful -> NetworkResult.Success(dogResponseToUIModel(response.body()!!.message))
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