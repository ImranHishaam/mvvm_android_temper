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

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imranhishaam.temper.R
import com.example.imranhishaam.temper.model.JobModel
import com.example.imranhishaam.temper.view.JobDetailActivity


class ItemJobViewModel(private var job: JobModel?, private val context: Context) : BaseObservable() {

    val clientName: String
        get() = job!!.client.name

    val title: String
        get() = job!!.title

    val distance: String
        get() = job!!.distance.toString() + " KM"

    val rating: String
        get() = if (job!!.client.rating == null) {
            "⭐ 0.0"
        } else "⭐ " + job!!.client.rating.average.toString()

    val cell: String
        get() = Integer.toString(job!!.shifts.size) + " open positie €" + java.lang.Double.toString(job!!.maxPossibleEarningsHour!!) + "/u"

    val jobCategoryPicture: String
        get() = "https://temper.works/assets/img/icon-category-bediening.svg"

    val pictureProfile: String
        get() = job!!.client.hero[0] as String

    fun onItemClick(view: View) {
        context.startActivity(JobDetailActivity.launchDetail(view.context, job!!))
    }

    fun setJob(job: JobModel) {
        this.job = job
        notifyChange()
    }

    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).apply(RequestOptions().placeholder(R.drawable.placeholder)).into(imageView)
        }
    }
}
