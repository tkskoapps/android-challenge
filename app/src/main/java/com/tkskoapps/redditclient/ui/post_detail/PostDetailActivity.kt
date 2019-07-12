package com.tkskoapps.redditclient.ui.post_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.ui.core.BaseActivity

class PostDetailActivity : BaseActivity() {

    companion object {

        fun getIntent(context: Context?): Intent {
            return Intent(context, PostDetailActivity::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_post_detail)

        replaceFragment(
            PostDetailFragment.newInstance(),
            R.id.activity_post_detail_frame_layout
        )

    }

}

