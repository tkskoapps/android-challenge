package com.tkskoapps.redditclient.ui.posts

import android.os.Bundle
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.ui.core.BaseActivity
import com.tkskoapps.redditclient.ui.post_detail.PostDetailActivity
import com.tkskoapps.redditclient.ui.post_detail.PostDetailFragment

class PostsActivity : BaseActivity() {

    private var landMode = false

    private lateinit var postsFragments: PostsFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)

        val postDetailContainer: FrameLayout? = findViewById<FrameLayout>(R.id.activity_posts_frame_detail)
        landMode = postDetailContainer != null

        if (savedInstanceState == null) {

            postsFragments = PostsFragment.newInstance()

            replaceFragment(postsFragments, R.id.activity_posts_frame_list)

        } else
            postsFragments =
                supportFragmentManager.getFragment(savedInstanceState, "postsFragment") as PostsFragment

        getViewModel().postDetail.observe(this, Observer { data ->
            data.getContentIfNotHandled()?.let { post ->
                if (landMode)
                    replaceFragment(PostDetailFragment.newInstance(post), R.id.activity_posts_frame_detail)
                else
                    startActivity(PostDetailActivity.getIntent(this, post))
            }
        })

        getViewModel().clearDetail.observe(this, Observer { data ->
            data.getContentIfNotHandled()?.let {
                clearPostDetail()
            }
        })

        clearPostDetail()

    }

    private fun clearPostDetail() {
        if (landMode)
            replaceFragment(PostDetailFragment.newInstance(), R.id.activity_posts_frame_detail)
    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)

        supportFragmentManager.putFragment(outState, "postsFragment", postsFragments)

    }

    fun getViewModel() = obtainViewModel(PostsViewModel::class.java)

}

