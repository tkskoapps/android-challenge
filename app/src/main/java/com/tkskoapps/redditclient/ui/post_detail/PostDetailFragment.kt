package com.tkskoapps.redditclient.ui.post_detail

import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.ui.core.BaseFragment

class PostDetailFragment : BaseFragment() {

    override fun getFragmentLayout() = R.layout.fragment_post_detail

    companion object {

        fun newInstance()= PostDetailFragment()

    }

}