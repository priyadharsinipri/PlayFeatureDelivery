package com.demo.centurion.shared.data.mappers

import com.demo.centurion.shared.data.models.InspectionResponse
import com.demo.centurion.shared.data.models.IssueResponse
import com.demo.centurion.shared.presentation.states.UIModel

fun catResponseToUIModel(cats: InspectionResponse): List<UIModel> {
    return cats.checklists?.map {
        UIModel(it.checklistNumber.toString())
    }!!
}

fun dogResponseToUIModel(dogs: IssueResponse): List<UIModel> {
    return dogs.issues?.map {
        UIModel(it.issueId.toString())
    }!!
}

