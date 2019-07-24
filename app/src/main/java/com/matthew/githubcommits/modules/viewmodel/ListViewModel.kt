package com.matthew.githubcommits.modules.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.matthew.githubcommits.base.BaseViewModel
import com.matthew.githubcommits.modules.ui.CommitUiModel
import com.matthew.githubcommits.modules.ui.CommitUiModel.*
import com.matthew.githubcommits.modules.ui.commit.CommitAdapter
import com.matthew.githubcommits.network.GithubApi
import kotlinx.coroutines.*
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration



class ListViewModel: BaseViewModel(), SwipeRefreshLayout.OnRefreshListener {

    companion object{
        const val TAG = "LIST_VIEW_MODEL"
    }

    @Inject
    lateinit var mApi: GithubApi

    val viewState: MutableLiveData<CommitUiModel> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val commitAdapter = CommitAdapter()

    private var mJob: Job? = null

    init{
        getCommits()
    }

    override fun onRefresh() {
        getCommits()
    }

    private fun getCommits(owner: String = "JetBrains", repo: String = "kotlin"){
        mJob = GlobalScope.launch(Dispatchers.Main) {
            try {
            loadingVisibility.postValue(View.VISIBLE)
                withTimeout(10000L) {
                    mApi.getCommits(owner = owner, repo = repo).await().body()?.let {
                        loadingVisibility.postValue(View.GONE)
                        commitAdapter.updateCommits(it)
                    }
                }
            } catch (timeout: Exception) {
                onError(timeout)
            }
        }
    }

    private fun onError(exception: Exception){
        loadingVisibility.postValue(View.GONE)

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

    fun getCommitAdapter(): CommitAdapter{
        return commitAdapter
    }

    fun showDividerLine(): Boolean{
        return true
    }

}