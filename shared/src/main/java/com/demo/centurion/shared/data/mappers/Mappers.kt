package com.demo.centurion.shared.data.mappers

import com.demo.centurion.shared.data.models.InspectionResponse
import com.demo.centurion.shared.data.models.IssueResponse
import com.demo.centurion.shared.presentation.states.UIIssueModel
import com.demo.centurion.shared.presentation.states.UIModel

fun catResponseToUIModel(cats: InspectionResponse): List<UIModel> {
    return cats.checklists?.map {
        UIModel(it.id, it.checklistTitle)
    }!!
}

fun dogResponseToUIModel(dogs: IssueResponse): List<UIIssueModel> {
    return dogs.issues?.map {
        UIIssueModel(it.issueNumber.toString(), it.description!!)
    }!!
}

