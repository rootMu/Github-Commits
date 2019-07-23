package com.matthew.githubcommits.modules.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.matthew.githubcommits.base.BaseViewModel
import com.matthew.githubcommits.modules.ui.CommitsUiModel
import com.matthew.githubcommits.modules.ui.CommitsUiModel.*
import com.matthew.githubcommits.network.GithubApi
import com.matthew.githubcommits.network.model.Commit
import kotlinx.coroutines.*
import javax.inject.Inject

class ListViewModel: BaseViewModel() {

    companion object{
        const val TAG = "LIST_VIEW_MODEL"
    }

    @Inject
    lateinit var mApi: GithubApi

    val viewState: MutableLiveData<CommitsUiModel> = MutableLiveData()

    private var mJob: Job? = null

    init{
        getCommits()
    }

    private fun getCommits(owner: String = "JetBrains", repo: String = "kotlin"){
        mJob = GlobalScope.launch {
            try {
                viewState.postValue(Loading(View.VISIBLE))
                withTimeout(15000L) {
                    mApi.getCommits(owner = owner, repo = repo).await().body()?.let {
                        viewState.postValue(Loading(View.GONE))
                        onSuccess(it)
                    }
                }
            } catch (timeout: Exception) {
                onError(timeout)
            }
        }
    }

    private fun onSuccess(commits: List<Commit>){
        Log.d(TAG, "there are ${commits.size} commits")
        viewState.postValue(CommitsUpdated(commits))
    }

    private fun onError(exception: Exception){
        when(exception){
            is TimeoutCancellationException -> {
                Log.e(TAG, "Search Timed out", exception)
            }
            else -> {
                Log.e(TAG, "Unknown exception whilst searching", exception)
            }
        }

        viewState.postValue(Error(exception))
    }

}