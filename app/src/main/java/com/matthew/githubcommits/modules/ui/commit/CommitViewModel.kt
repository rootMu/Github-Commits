package com.matthew.githubcommits.modules.ui.commit

import androidx.lifecycle.MutableLiveData
import com.matthew.githubcommits.base.BaseViewModel
import com.matthew.githubcommits.network.model.Commit
import com.matthew.githubcommits.utils.fromISO8601UTC

class CommitViewModel: BaseViewModel() {
    private val commitTitle = MutableLiveData<String>()
    private val commitTime = MutableLiveData<String>()
    private val authorName = MutableLiveData<String>()
    private val authorImage = MutableLiveData<String?>()

    fun bind(newCommit: Commit){
        with(newCommit){
            commitTitle.postValue(commit.message)
            commitTime.postValue(commit.author.date.fromISO8601UTC())
            authorName.postValue(commit.author.name)
            authorImage.postValue(author?.avatar_url)
        }
    }

    fun getCommitTitle(): MutableLiveData<String>{
        return commitTitle
    }

    fun getCommitTime(): MutableLiveData<String>{
        return commitTime
    }

    fun getAuthorName(): MutableLiveData<String>{
        return authorName
    }

    fun getAuthorImage(): MutableLiveData<String?>{
        return authorImage
    }

}