package com.demo.centurion.shared.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Issue(
    @SerialName("id")
    var issueId: String? = null,

    @SerialName("issueNumber")
    var issueNumber: String? = null
)
