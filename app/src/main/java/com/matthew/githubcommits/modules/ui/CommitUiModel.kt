package com.matthew.githubcommits.modules.ui

import com.matthew.githubcommits.network.model.Commit as IndividualCommit
import java.lang.Exception

sealed class CommitUiModel {
    data class CommitsUpdated(val commits: List<IndividualCommit>) : CommitUiModel()
    data class Error(val exception: Exception) : CommitUiModel()
}