package com.matthew.githubcommits.modules.ui

import com.matthew.githubcommits.network.model.Commit
import java.lang.Exception

sealed class CommitsUiModel {

    data class Loading(val loadingVisibility: Int) : CommitsUiModel()
    data class CommitsUpdated(val commits: List<Commit>) : CommitsUiModel()
    data class Error(val exception: Exception) : CommitsUiModel()
    object Reset : CommitsUiModel()

}