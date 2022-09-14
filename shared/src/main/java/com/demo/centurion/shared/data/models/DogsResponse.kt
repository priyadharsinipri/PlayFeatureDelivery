
package com.demo.centurion.shared.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogsResponse(
    @SerialName("message")
    val message: List<String>,
    @SerialName("status")
    val status: String
)