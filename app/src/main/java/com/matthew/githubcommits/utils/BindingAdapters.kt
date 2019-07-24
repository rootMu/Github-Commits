package com.matthew.githubcommits.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.DividerItemDecoration
import com.matthew.githubcommits.R

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableImage")
fun setMutableImage(view: ImageView, text: MutableLiveData<String?>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            //update to use all images with a custom view pager
            value?.let{
                Picasso.get()
                    .load(it)
                    .into(view)
            }?:run{
                view.setImageDrawable(parentActivity.getDrawable(R.drawable.default_avatar))
            }
        })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("onRefreshListener")
fun setOnRefreshListener(view: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener){
    view.setOnRefreshListener(listener)
}

@BindingAdapter("isRefreshing")
fun setIsRefreshing(view: SwipeRefreshLayout, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            if(value == View.GONE)
                view.isRefreshing = false
        })
    }
}

@BindingAdapter("dividerLineDecoration")
fun setDividerLineDecoration(view: RecyclerView, show: Boolean){
    if(show){
        val dividerItemDecoration = DividerItemDecoration(
            view.context,
            RecyclerView.VERTICAL
        )
        view.addItemDecoration(dividerItemDecoration)
    }
}
