
package com.demo.centurion.shared.data.network

import com.demo.centurion.shared.data.models.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsAPI {
  @GET("breed/hound/images/random/{number}")
  suspend fun getDogs(
      @Path("number") number: String = "30"
  ): Response<DogsResponse>
}