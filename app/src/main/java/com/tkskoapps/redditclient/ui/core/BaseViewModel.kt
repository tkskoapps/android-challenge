package com.tkskoapps.redditclient.ui.core

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private var _disposable: CompositeDisposable? = null

    fun addSubscription(subscription: Disposable) {

        if (_disposable == null)
            _disposable = CompositeDisposable()

        _disposable!!.add(subscription)

    }

    override fun onCleared() {
        super.onCleared()
        if (_disposable != null) {
            _disposable?.clear()
            _disposable = null
        }
    }

}