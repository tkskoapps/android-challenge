package com.tkskoapps.redditclient.ui.core

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    open fun replaceFragment(fragment: BaseFragment, @IdRes containerId: Int) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(0, 0)
            .replace(containerId, fragment).commit()
    }

}