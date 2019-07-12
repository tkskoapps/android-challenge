/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.tkskoapps.redditclient.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkskoapps.redditclient.data.api.RedditAPI
import com.tkskoapps.redditclient.data.api.RemoteDataSource
import com.tkskoapps.redditclient.data.api.ServiceGenerator
import com.tkskoapps.redditclient.data.repositories.AppRepository
import com.tkskoapps.redditclient.ui.posts.PostsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(PostsViewModel::class.java) ->
                    PostsViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                            AppRepository(
                                RemoteDataSource(ServiceGenerator().createService(RedditAPI::class.java))
                            )
                        )
                            .also { INSTANCE = it }
                }

    }

}
