/**
 * Copyright 2016 Erik imranhishaam Rey.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.imranhishaam.temper.viewmodel

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imranhishaam.temper.R
import com.example.imranhishaam.temper.model.JobModel

class JobDetailViewModel(private val job: JobModel) {

    val fullUserName: String
        get() = job.client.name

    val description: String
        get() = job.client.description

    val userName: String
        get() = job.client.name

    val date: String
        get() = job.date

    val cell: String
        get() = job.distance!!.toString() + " KM"

    val pictureProfile: String
        get() = job.client.hero[0] as String

    val url: String
        get() = job.url

    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context).load(imageUrl).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(view)
        }
    }
}
