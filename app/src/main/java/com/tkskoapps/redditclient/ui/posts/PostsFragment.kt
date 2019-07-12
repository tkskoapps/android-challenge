package com.tkskoapps.redditclient.ui.posts

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.ui.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.view_recycler_view.*

class PostsFragment : BaseFragment() {

    private lateinit var postsAdapter: PostsAdapter

    private lateinit var postViewModel: PostsViewModel

    companion object {

        fun newInstance() = PostsFragment()

    }

    override fun getFragmentLayout() = R.layout.fragment_posts

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        postsAdapter = PostsAdapter()

        retainInstance = true

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        postViewModel = (activity as PostsActivity).getViewModel()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        initView()

        getPage()

    }

    private fun initView() {

        view_recycle_view_list.adapter = postsAdapter

        view_recycle_view_list.setHasFixedSize(true)

        view_recycle_view_swipe_refresh_layout.setOnRefreshListener {
            getPage()
        }

        postViewModel.postsList.observe(this, Observer { data ->

            data.getContentIfNotHandled()?.let {

                postsAdapter.updateList(it.posts.toMutableList())

                setEmptyViewVisibility()

            }

        })

        postViewModel.loading.observe(this, Observer { data ->

            data.getContentIfNotHandled()?.let { loading ->
                view_recycle_view_swipe_refresh_layout.isRefreshing = loading
            }

        })

        view_recycle_view_button_reload.setOnClickListener {
            getPage()
        }

    }

    private fun setEmptyViewVisibility() {

        val emptyList = postsAdapter.itemCount == 0

        view_recycle_view_layout_empty.visibility = if (emptyList) View.VISIBLE else View.GONE

        fragment_posts_button_clean.visibility = if (!emptyList) View.VISIBLE else View.GONE

    }

    private fun getPage(pageNumber: Int = 0) {

        postViewModel.getPosts(pageNumber)

    }

}