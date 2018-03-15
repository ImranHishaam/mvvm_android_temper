package com.example.imranhishaam.temper.view

/**
 * Created by imranhishaam on 3/13/18.
 */

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

import com.example.imranhishaam.temper.R
import com.example.imranhishaam.temper.databinding.JobDetailActivityBinding
import com.example.imranhishaam.temper.model.JobModel
import com.example.imranhishaam.temper.viewmodel.JobDetailViewModel

class JobDetailActivity : AppCompatActivity() {

    private var jobDetailActivityBinding: JobDetailActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jobDetailActivityBinding = DataBindingUtil.setContentView<JobDetailActivityBinding>(this, R.layout.job_detail_activity)
        setSupportActionBar(jobDetailActivityBinding!!.toolbar)
        displayHomeAsUpEnabled()
        getExtrasFromIntent()
    }

    private fun displayHomeAsUpEnabled() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getExtrasFromIntent() {
        val job = intent.getParcelableExtra<Parcelable>(EXTRA_JOB) as JobModel
        val jobDetailViewModel = JobDetailViewModel(job)
        jobDetailActivityBinding!!.jobDetailViewModel = jobDetailViewModel
        title = job.title + "." + job.client + " " + job.title
    }

    companion object {

        private val EXTRA_JOB = "EXTRA_JOB"

        fun launchDetail(context: Context, job: JobModel): Intent {
            val intent = Intent(context, JobDetailActivity::class.java)
            intent.putExtra(EXTRA_JOB, job)
            return intent
        }
    }
}
