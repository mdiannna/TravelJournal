package com.example.traveljournal.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.traveljournal.viewmodels.BasicViewModel

abstract class BaseActivity<T : BaseNavigator, V : BasicViewModel<T>> : AppCompatActivity(), BaseNavigator {

    var viewModel: V? = null
    abstract fun getViewModels(): V?

    override fun showLoading() {

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        if (this.viewModel == null) this.viewModel = getViewModels()
    }


}