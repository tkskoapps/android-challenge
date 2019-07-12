package com.tkskoapps.redditclient.ui.posts

import android.os.Bundle
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.ui.core.BaseActivity

class PostsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)

        replaceFragment(PostsFragment.newInstance(), R.id.activity_posts_frame_list)

    }

}

