package com.tkskoapps.redditclient.ui.posts

import android.os.Bundle
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.ui.core.BaseFragment

class PostsFragment : BaseFragment() {

    companion object {

        fun newInstance() = PostsFragment()

    }

    override fun getFragmentLayout() = R.layout.fragment_posts

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        retainInstance = true

    }

}