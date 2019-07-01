# Android Challenge

## Objective
We would like to have you complete the following code test so we can evaluate your Android skills.  Please place your code in a public Github repository and commit each step of your process so we can review it.

Your assignment is to create a simple Reddit client that shows the top 50 entries from Reddit.

## Show your work

1.  Create a Public repository
2.  Commit each step of your process so we can follow your thought process.

## Guidelines
To do this please follow these guidelines:

    - Assume the latest platform and use Kotlin
    - Follow one of design patterns to create flexible and scalable app (i.e. MVVM/MVP/MVC)
    - Use Retrofit/GSON and Kotlin data classes for working with API
    - Use Android Jetpack components (e.g. AppCompat, ViewModel, LiveData, Paging etc.)
    - Use RecyclerView and ConstraintLayout to arrange the data and provide flexible UI
    - Support all Device Orientation

## What to show
The app should be able to show data from each entry such as:

    - Title (at its full length, so take this into account when sizing your cells)
    - Author
    - entry date, following a format like “x hours ago”
    - A thumbnail for those who have a picture.
    - Number of comments
    - Unread status

In addition, for those having a picture (besides the thumbnail), please allow the user to tap on the thumbnail to be sent to the full sized picture. You don’t have to implement the IMGUR API, so just opening the URL would be OK.

## What to Include

    - Pull to Refresh
    - Pagination support
    - Saving pictures in the picture gallery (Optional)
    - App state-preservation/restoration
    - Indicator of unread/read post (updated status, after post it’s selected)
    - Dismiss Post Button (remove the cell from list. Animations required)
    - Dismiss All Button (remove all posts. Animations required)
    - Ignore iPad do it just for iPhone. (So the list and the detail view)

## Resources

    - Reddit API: (Please read from 10 to 10 on each so we can see how you handle pagination.)
    GET: https://www.reddit.com/r/all/top/.json?t=all&limit=10

    In each page you will have an "after" key and "before" so you can read the key "after" and append it to the url like so to get the next 10 posts.
    https://www.reddit.com/r/all/top/.json?t=all&limit=10&after={{value_of_after_key}}

    - Example JSON file (top.json) is listed.
    - Example Video of functionality is attached

## Time Spent
You do not need to fully complete the challenge. We suggest not to spend more than 5 hours total, which can be done over the course of 2 days.  Please make commits as often as possible so we can see the time you spent and please do not make one commit.  We will evaluate the code and time spent.

What we want to see is how well you handle yourself given the time you spend on the problem, how you think, and how you prioritize when time is insufficient to solve everything.

Whenever you finish send us your repository.
