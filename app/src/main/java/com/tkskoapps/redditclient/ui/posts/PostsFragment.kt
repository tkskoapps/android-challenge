package com.tkskoapps.redditclient.ui.posts

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.data.model.PostModel
import com.tkskoapps.redditclient.ui.core.BaseFragment
import com.tkskoapps.redditclient.ui.post_detail.PostDetailActivity
import com.tkskoapps.redditclient.ui.utils.AppConstants
import com.tkskoapps.redditclient.ui.utils.InfiniteOnScrollListener
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.view_recycler_view.*

class PostsFragment : BaseFragment(), PostsAdapter.IPostsListener {

    private lateinit var postsAdapter: PostsAdapter
    private lateinit var infiniteOnScrollListener: InfiniteOnScrollListener

    private lateinit var postViewModel: PostsViewModel

    companion object {

        fun newInstance() = PostsFragment()

    }

    override fun getFragmentLayout() = R.layout.fragment_posts

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        postsAdapter = PostsAdapter(listener = this)

        retainInstance = true

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        postViewModel = (activity as PostsActivity).getViewModel()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        initView()

        if (savedInstanceState == null)
            getPage()
        else
            setEmptyViewVisibility()

    }

    private fun initView() {

        view_recycle_view_list.adapter = postsAdapter

        view_recycle_view_list.setHasFixedSize(true)

        view_recycle_view_swipe_refresh_layout.setOnRefreshListener {
            getPage()
        }

        infiniteOnScrollListener =
            object : InfiniteOnScrollListener(
                view_recycle_view_list.layoutManager as LinearLayoutManager,
                view_recycle_view_swipe_refresh_layout,
                null
            ) {

                override fun onLoadMore(page: Int) {

                    getPage(page)

                }

            }

        view_recycle_view_list.addOnScrollListener(infiniteOnScrollListener)

        postViewModel.postsList.observe(this, Observer { data ->

            data.getContentIfNotHandled()?.let {

                infiniteOnScrollListener.isLimitReached =
                    it.posts.isNullOrEmpty() || it.posts.size < AppConstants.POSTS_PAGE_SIZE

                postsAdapter.updateList(it.posts.toMutableList(), it.lastItemId, it.pageNumber)

                setEmptyViewVisibility()

            }

        })

        postViewModel.loading.observe(this, Observer { data ->

            data.getContentIfNotHandled()?.let { loading ->

                view_recycle_view_swipe_refresh_layout.isRefreshing = loading
                infiniteOnScrollListener.isLoading = loading

            }

        })

        view_recycle_view_button_reload.setOnClickListener {
            getPage()
        }

        fragment_posts_button_clean.setOnClickListener {

            infiniteOnScrollListener.reset()

            postsAdapter.clearAllPosts()

            setEmptyViewVisibility()

        }

    }

    private fun setEmptyViewVisibility() {

        val emptyList = postsAdapter.itemCount == 0

        view_recycle_view_layout_empty.visibility = if (emptyList) View.VISIBLE else View.GONE

        fragment_posts_button_clean.visibility = if (!emptyList) View.VISIBLE else View.GONE

    }

    private fun getPage(pageNumber: Int = 0) {

        if (pageNumber == 0) {
            postsAdapter.lastItemId = null
            infiniteOnScrollListener.reset()
        }

        postViewModel.getPosts(pageNumber, postsAdapter.lastItemId)

    }

    override fun onPostSelected(post: PostModel) {

        startActivity(PostDetailActivity.getIntent(activity, post.postData))

    }


}