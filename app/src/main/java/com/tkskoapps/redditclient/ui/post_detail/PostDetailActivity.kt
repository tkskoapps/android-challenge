package com.tkskoapps.redditclient.ui.post_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.data.model.PostDataModel
import com.tkskoapps.redditclient.ui.core.BaseActivity
import com.tkskoapps.redditclient.ui.utils.AppConstants.Companion.INTENT_PARAM_POST

class PostDetailActivity : BaseActivity() {

    companion object {

        fun getIntent(context: Context?, post: PostDataModel): Intent {
            return Intent(context, PostDetailActivity::class.java).apply {
                putExtra(INTENT_PARAM_POST, post)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_post_detail)

        replaceFragment(
            PostDetailFragment.newInstance(intent.getParcelableExtra(INTENT_PARAM_POST)),
            R.id.activity_post_detail_frame_layout
        )

    }

}

