package com.tkskoapps.redditclient.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tkskoapps.redditclient.data.model.PostDataModel
import com.tkskoapps.redditclient.data.model.PostListModel
import com.tkskoapps.redditclient.data.repositories.AppRepository
import com.tkskoapps.redditclient.ui.core.BaseViewModel
import com.tkskoapps.redditclient.ui.core.Event
import com.tkskoapps.redditclient.ui.utils.AppConstants
import io.reactivex.observers.DisposableSingleObserver

class PostsViewModel(private val repository: AppRepository) :
    BaseViewModel() {

    private var _loading = MutableLiveData<Event<Boolean>>()
    val loading: LiveData<Event<Boolean>>
        get() = _loading

    private var _postsList = MutableLiveData<Event<PostListModel>>()
    val postsList: LiveData<Event<PostListModel>>
        get() = _postsList

    private var _postDetail = MutableLiveData<Event<PostDataModel>>()
    val postDetail: LiveData<Event<PostDataModel>>
        get() = _postDetail

    fun getPosts(pageNumber: Int, lastItemId: String? = null) {

        val pageSize = AppConstants.POSTS_PAGE_SIZE

        _loading.postValue(Event(true))

        addSubscription(
            repository.getPosts(
                lastItemId,
                "all",
                (pageNumber * pageSize).toString(),
                pageSize.toString()
            ).subscribeWith(object :
                DisposableSingleObserver<PostListModel>() {
                override fun onSuccess(postsList: PostListModel) {
                    postsList.pageNumber = pageNumber
                    _postsList.postValue(Event(postsList))
                    _loading.postValue(Event(false))
                }

                override fun onError(e: Throwable) {
                    _loading.postValue(Event(false))
                }
            })
        )

    }

    fun openPostDetail(post: PostDataModel) {
        _postDetail.postValue(Event(post))
    }

}