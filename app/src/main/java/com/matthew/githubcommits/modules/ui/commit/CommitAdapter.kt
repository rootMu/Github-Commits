package com.matthew.githubcommits.modules.ui.commit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.matthew.githubcommits.R
import com.matthew.githubcommits.network.model.Commit
import com.matthew.githubcommits.databinding.ItemCommitBinding

class CommitAdapter: RecyclerView.Adapter<CommitAdapter.ViewHolder>() {
    private lateinit var commits:List<Commit>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCommitBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_commit, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commits[position])
    }

    override fun getItemCount(): Int {
        return if(::commits.isInitialized) commits.size else 0
    }

    fun updateCommits(commits:List<Commit>){
        this.commits = commits
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCommitBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = CommitViewModel()
        fun bind(post:Commit){
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}