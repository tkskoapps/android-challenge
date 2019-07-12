package com.tkskoapps.redditclient.ui.utils

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

abstract class InfiniteOnScrollListener(
    private val mLinearLayoutManager: LinearLayoutManager?,
    private val swipeRefreshLayout: SwipeRefreshLayout?,
    private val mProgress: View?
) : RecyclerView.OnScrollListener() {

    // true if we are still waiting for the last set of data to load.
    var isLoading = false

    // the current page being displayed/loaded
    var currentPage = 0
        private set

    // if there is no more data to load, we stop fetching from the server
    var isLimitReached = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (mLinearLayoutManager == null)
            return

        // first and last visible items of the list
        val firstVisibleItem = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition()
        val lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition()

        if (swipeRefreshLayout != null) {

            swipeRefreshLayout.isEnabled =
                firstVisibleItem == 0 && (mProgress == null || mProgress.visibility == View.GONE)
        }

        if (isLimitReached) return

        // when the user reaches the bottom of the list, we fetch more data
        if (!isLoading && lastVisibleItem == recyclerView.adapter!!.itemCount - 1) {

            // load next page
            currentPage++

            onLoadMore(currentPage)
        }

    }

    /**
     * Start loading from the first page again.
     */
    fun reset() {
        this.isLoading = true
        this.currentPage = 0
    }

    abstract fun onLoadMore(currentPage: Int)
}