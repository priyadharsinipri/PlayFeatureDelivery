package com.demo.centurion.shared.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IssueResponse(
    @SerialName("issues")
    var issues: List<Issue>? = emptyList(),


)
