package com.tkskoapps.redditclient.ui.posts

import android.os.Bundle
import android.view.View
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.ui.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.view_recycler_view.*

class PostsFragment : BaseFragment() {

    private lateinit var postsAdapter: PostsAdapter

    companion object {

        fun newInstance() = PostsFragment()

    }

    override fun getFragmentLayout() = R.layout.fragment_posts

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        postsAdapter = PostsAdapter()

        retainInstance = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setEmptyViewVisibility()

    }

    private fun setEmptyViewVisibility() {

        val emptyList = postsAdapter.itemCount == 0

        view_recycle_view_layout_empty.visibility = if (emptyList) View.VISIBLE else View.GONE

        fragment_posts_button_clean.visibility = if (!emptyList) View.VISIBLE else View.GONE

    }

}