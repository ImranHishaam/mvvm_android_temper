package com.example.imranhishaam.temper.view

/**
 * Created by imranhishaam on 3/13/18.
 */

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.imranhishaam.temper.R
import com.example.imranhishaam.temper.databinding.ItemJobBinding
import com.example.imranhishaam.temper.model.JobModel
import com.example.imranhishaam.temper.viewmodel.ItemJobViewModel

import java.util.ArrayList
import java.util.Collections

class MainAdapter : RecyclerView.Adapter<MainAdapter.JobAdapterViewHolder>() {

    private var jobList: List<JobModel>? = null

    init {
        this.jobList = emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobAdapterViewHolder {
        val itemJobBinding = DataBindingUtil.inflate<ItemJobBinding>(LayoutInflater.from(parent.context), R.layout.item_job,
                parent, false)
        return JobAdapterViewHolder(itemJobBinding)
    }

    override fun onBindViewHolder(holder: JobAdapterViewHolder, position: Int) {
        holder.bindJobs(jobList!![position])
    }

    override fun getItemCount(): Int {
        return jobList!!.size
    }

    fun setJobList(jobList: ArrayList<JobModel>) {
        this.jobList = jobList
        notifyDataSetChanged()
    }

    class JobAdapterViewHolder(internal var mItemJobBinding: ItemJobBinding?) : RecyclerView.ViewHolder(mItemJobBinding!!.itemJob) {

        internal fun bindJobs(job: JobModel) {
            if (mItemJobBinding!!.jobViewModel == null) {
                mItemJobBinding!!.jobViewModel = ItemJobViewModel(job, itemView.context)
            } else {
                mItemJobBinding!!.jobViewModel!!.setJob(job)
            }
        }
    }
}
