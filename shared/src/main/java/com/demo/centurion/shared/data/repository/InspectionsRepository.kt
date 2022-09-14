package com.demo.centurion.shared.data.repository

import android.util.Log
import com.demo.centurion.shared.data.mappers.catResponseToUIModel
import com.demo.centurion.shared.data.network.InspectionsAPI
import com.demo.centurion.shared.data.network.NetworkResult
import com.demo.centurion.shared.presentation.states.UIModel
import com.demo.centurion.shared.utils.Constants
import java.io.IOException

interface InspectionsRepository {
    suspend fun getCats(): NetworkResult<List<UIModel>>
}

class InspectionsRepositoryImpl(
    private val inspectionsAPI: InspectionsAPI
) : InspectionsRepository {
    override suspend fun getCats(): NetworkResult<List<UIModel>> =
        try {
            val url =
                Constants.BASE_URL + "/mobile/rest/checklists/project/26851/area/2801?page_number=1&page_size=30"
            val response = inspectionsAPI.getInspections(url)
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