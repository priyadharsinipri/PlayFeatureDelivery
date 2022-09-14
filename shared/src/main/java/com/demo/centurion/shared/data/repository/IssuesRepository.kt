package com.demo.centurion.shared.data.repository

import android.util.Log
import com.demo.centurion.shared.data.mappers.dogResponseToUIModel
import com.demo.centurion.shared.data.network.IssuesAPI
import com.demo.centurion.shared.data.network.NetworkResult
import com.demo.centurion.shared.presentation.states.UIModel
import com.demo.centurion.shared.utils.Constants
import java.io.IOException

interface IssuesRepository {
    suspend fun getIssues(): NetworkResult<List<UIModel>>
}

class IssuesRepositoryImpl(
    private val issuesAPI: IssuesAPI
) : IssuesRepository {

    override suspend fun getIssues(): NetworkResult<List<UIModel>> =
        try {
            val url =
                Constants.BASE_URL + "/mobile/rest/issues/26851/2801?page_number=1&page_size=30"
            val response = issuesAPI.getIssues(url)
            Log.e("Issue Url", url)
            when {
                response.isSuccessful -> NetworkResult.Success(dogResponseToUIModel(response.body()!!))
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