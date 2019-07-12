package com.tkskoapps.redditclient.ui.posts

import android.os.Bundle
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.ui.core.BaseActivity

class PostsActivity : BaseActivity() {

    private lateinit var postsFragments: PostsFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)

        if (savedInstanceState == null) {

            postsFragments = PostsFragment.newInstance()

            replaceFragment(postsFragments, R.id.activity_posts_frame_list)

        } else
            postsFragments =
                supportFragmentManager.getFragment(savedInstanceState, "postsFragment") as PostsFragment

    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)

        supportFragmentManager.putFragment(outState, "postsFragment", postsFragments)

    }

    fun getViewModel() = obtainViewModel(PostsViewModel::class.java)

}

