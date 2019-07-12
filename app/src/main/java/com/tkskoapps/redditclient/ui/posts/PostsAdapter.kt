package com.tkskoapps.redditclient.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tkskoapps.redditclient.R
import com.tkskoapps.redditclient.data.model.PostModel
import kotlinx.android.synthetic.main.list_item_post.view.*

class PostsAdapter(
    private var items: MutableList<PostModel> = mutableListOf(),
    var listener: IPostsListener? = null,
    var lastItemId: String? = null
) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)

        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        items[position].let { post ->

            holder.bindCardData(post)

            holder.itemView.list_item_post_main_layout.setOnClickListener {

                if (!post.read) {
                    post.read = true
                    notifyItemChanged(position)
                }

                listener?.onPostSelected(post)

            }

            holder.itemView.list_item_post_button_delete.setOnClickListener {

                items.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, items.size)

            }

        }

    }

    fun updateList(list: MutableList<PostModel>, newLastItemId: String?, pageNumber: Int) {

        val count = items.size

        lastItemId = newLastItemId

        if (pageNumber == 0) {

            items = list

            this.notifyItemRangeRemoved(0, count)

            this.notifyItemRangeInserted(0, items.size)

        } else {

            val positionStart = items.size

            items.addAll(list)

            this.notifyItemRangeInserted(positionStart, list.size)

        }

    }

    fun clearAllPosts() {

        val count = items.size

        items.clear()

        notifyItemRangeRemoved(0, count)

    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindCardData(post: PostModel) {

            post.postData.let { postData ->

                Glide.with(itemView.context).load(postData.thumbnail).placeholder(R.drawable.empty_placeholder)
                    .into(itemView.list_item_post_thumbnail)

                itemView.list_item_post_title.text = postData.title

                itemView.list_item_post_author.text = postData.author

                itemView.list_item_post_time_ago.text = "TODO"

                itemView.list_item_post_comments_count.text = postData.commentsCount.toString()

            }

            itemView.list_item_post_read_status.visibility = if (post.read) View.GONE else View.VISIBLE

        }

    }

    interface IPostsListener {

        fun onPostSelected(post: PostModel)

    }

}