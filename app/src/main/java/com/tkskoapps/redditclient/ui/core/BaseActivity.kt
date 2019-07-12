package com.tkskoapps.redditclient.ui.core

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

open class BaseActivity : AppCompatActivity() {

    open fun replaceFragment(fragment: BaseFragment, @IdRes containerId: Int) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(0, 0)
            .replace(containerId, fragment).commit()
    }

    fun <T : ViewModel> obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(viewModelClass)

}