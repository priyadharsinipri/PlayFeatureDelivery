package com.demo.centurion.shared.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Issue(

    @SerialName("issueNumber")
    var issueNumber: String? = null,

    @SerialName("description")
    var description: String? = null
)
