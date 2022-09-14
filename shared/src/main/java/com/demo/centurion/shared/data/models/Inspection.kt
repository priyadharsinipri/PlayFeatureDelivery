package com.demo.centurion.shared.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Inspection(
    @SerialName("id")
    var id: String? = "",

    @SerialName("number")
    var checklistNumber: String? = null,

    @SerialName("title")
    var checklistTitle: String? = null,

    @SerialName("status")
    var checklistStatus: String? = null,
)
