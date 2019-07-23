package com.matthew.githubcommits.modules.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.matthew.githubcommits.R
import com.matthew.githubcommits.modules.viewmodel.ListViewModel
import com.matthew.githubcommits.modules.ui.CommitsUiModel.*

class ListActivity : AppCompatActivity() {

    private lateinit var mViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialiseViewModel()
    }
    /**
     *  Initialises the view model [mViewModel] or reuses the existing one
     */
    private fun initialiseViewModel() {
        mViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java).apply{
            viewState.observe(this@ListActivity, Observer { it ->
                it?.let { handleLiveData(it) }
            })
        }
    }

    private fun handleLiveData(uiModel: CommitsUiModel){
        when(uiModel){
            is Loading -> {
                /**
                 * show hide loading visual with value passed in
                 * @param uiModel.loadingVisibility
                 */
            }
            is Error -> {
                /**
                 * show correct error state
                 * @param uiModel.exception
                 */
            }
        }
    }


}
