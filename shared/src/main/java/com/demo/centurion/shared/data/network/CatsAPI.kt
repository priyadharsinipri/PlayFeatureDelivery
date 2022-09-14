
package com.demo.centurion.shared.data.network

import com.demo.centurion.shared.data.models.CatsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsAPI {

  @GET("cats")
  suspend fun getCats(
      @Query("tags") tag: String = "cute",
      @Query("skip") skip: Int = 0,
      @Query("limit") limit: Int = 30
  ): Response<List<CatsResponse>>
}