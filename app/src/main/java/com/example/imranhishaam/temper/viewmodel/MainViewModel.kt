/**
 * Copyright 2016 Erik imranhishaam Rey.
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package com.example.imranhishaam.temper.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View

import com.example.imranhishaam.temper.BuildConfig
import com.example.imranhishaam.temper.TemperApplication
import com.example.imranhishaam.temper.R
import com.example.imranhishaam.temper.data.JobFactory
import com.example.imranhishaam.temper.data.JobResponse
import com.example.imranhishaam.temper.data.JobService
import com.example.imranhishaam.temper.model.JobModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.util.ArrayList
import java.util.HashMap
import java.util.Observable

class MainViewModel(private var context: Context?) : Observable() {

    var jobProgress: ObservableInt? = null
    var jobRecycler: ObservableInt? = null
    var jobLabel: ObservableInt? = null
    var messageLabel: ObservableField<String> ? = null

    private val jobList: MutableMap<String, ArrayList<JobModel>>
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    init {
        this.jobList = HashMap()
        jobProgress = ObservableInt(View.GONE)
        jobRecycler = ObservableInt(View.GONE)
        jobLabel = ObservableInt(View.VISIBLE)
        messageLabel = ObservableField(context!!.getString(R.string.default_loading_job))
    }

    fun onClickFabLoad(view: View) {
        initializeViews()
        fetchJobList()
    }

    private fun initializeViews() {
        jobLabel?.set(View.GONE)
        jobRecycler?.set(View.GONE)
        jobProgress?.set(View.VISIBLE)
    }

    fun fetchJobList() {

        val temperApplication = TemperApplication.create(context!!)
        val jobService = temperApplication.jobService

        val disposable = jobService!!.fetchJobs(BuildConfig.HOST_URL + BuildConfig.JOBS_URL)
                .subscribeOn(temperApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ jobResponse ->
                    changeJobDataSet(jobResponse.jobList!!)
                    jobProgress?.set(View.GONE)
                    jobLabel?.set(View.GONE)
                    jobRecycler?.set(View.VISIBLE)
                }) {
                    messageLabel?.set(context!!.getString(R.string.error_loading_job))
                    jobProgress?.set(View.GONE)
                    jobLabel?.set(View.VISIBLE)
                    jobRecycler?.set(View.GONE)
                }

        compositeDisposable!!.add(disposable)
    }

    private fun changeJobDataSet(jobs: Map<String, ArrayList<JobModel>>) {
        jobList.putAll(jobs)
        setChanged()
        notifyObservers()
    }

    fun getJobList(): Map<String, ArrayList<JobModel>> {
        return jobList
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
        context = null
    }
}
