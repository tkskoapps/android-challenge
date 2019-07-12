package com.tkskoapps.redditclient.ui.post_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.data.model.PostDataModel
import com.tkskoapps.redditclient.ui.core.BaseFragment
import com.tkskoapps.redditclient.ui.utils.AppConstants.Companion.INTENT_PARAM_POST
import kotlinx.android.synthetic.main.fragment_post_detail.*

class PostDetailFragment : BaseFragment() {

    override fun getFragmentLayout() = R.layout.fragment_post_detail

    companion object {

        fun newInstance(post: PostDataModel? = null): PostDetailFragment {
            return PostDetailFragment().apply {
                arguments = bundleOf(
                    Pair(INTENT_PARAM_POST, post ?: PostDataModel())
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        loadPostData()

    }

    private fun loadPostData() {

        arguments?.getParcelable<PostDataModel>(INTENT_PARAM_POST)?.let { post ->

            fragment_post_detail_title.text = post.title

            if (!post.thumbnail.isNullOrEmpty()) {

                Glide.with(this).load(post.thumbnail).placeholder(R.drawable.empty_placeholder)
                    .into(fragment_post_detail_thumbnail)

                fragment_post_detail_thumbnail.setOnClickListener {

                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(post.url)))
                }

            } else
                fragment_post_detail_thumbnail.visibility = View.GONE

        }

    }

}