package com.demo.centurion.shared.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InspectionResponse(
    @SerialName("checklists")
    var checklists: List<Inspection> = emptyList()

)
