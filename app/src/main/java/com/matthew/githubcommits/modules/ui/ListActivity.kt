package com.matthew.githubcommits.modules.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.matthew.githubcommits.R
import com.matthew.githubcommits.databinding.ActivityListBinding
import com.matthew.githubcommits.modules.viewmodel.ListViewModel
import com.matthew.githubcommits.modules.ui.CommitUiModel.*
import com.matthew.githubcommits.utils.nonNullObserve
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(){

    private lateinit var mViewModel: ListViewModel
    private lateinit var binding: ActivityListBinding

    companion object{
        const val TAG_ERROR_POPUP = "TAG_ERROR_POPUP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialiseDataBinding()
        initialiseViewModel()
    }

    private fun initialiseDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.commitList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    /**
     *  Initialises the view model [mViewModel] or reuses the existing one
     */
    private fun initialiseViewModel() {
        mViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java).apply{
            viewState.nonNullObserve(this@ListActivity){
                handleLiveData(it)
            }

            binding.viewModel = this
        }
    }

    private fun handleLiveData(uiModel: CommitUiModel){
        when(uiModel){
            is Error -> {
                ErrorPopup().show(
                    supportFragmentManager,
                    TAG_ERROR_POPUP
                )
                /**
                 * show correct error state
                 * @param uiModel.exception
                 */
            }
        }
    }


}
