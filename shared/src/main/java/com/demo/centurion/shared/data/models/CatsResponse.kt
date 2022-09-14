
package com.demo.centurion.shared.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatsResponse(
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("id")
    val id: String,
    @SerialName("tags")
    val tags: List<String>
)