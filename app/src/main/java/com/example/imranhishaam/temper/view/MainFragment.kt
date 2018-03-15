package com.example.imranhishaam.temper.view

import android.os.Parcelable
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.imranhishaam.temper.R
import com.example.imranhishaam.temper.model.JobModel
import com.example.imranhishaam.temper.viewmodel.MainViewModel

import java.util.ArrayList

/**
 * Created by imranhishaam on 3/14/18.
 */

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val recyclerView = inflater.inflate(R.layout.fragment_main, container, false) as RecyclerView
        setupRecyclerView(recyclerView)
        return recyclerView
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val recyclerAdapter = MainAdapter()
        val bundle = arguments
        val jobList = bundle!!.getParcelableArrayList<JobModel>(ITEMS_COUNT_KEY) as ArrayList<JobModel>
        recyclerAdapter.setJobList(jobList)
        recyclerView.adapter = recyclerAdapter
    }

    companion object {
        val ITEMS_COUNT_KEY = "MainFragment\$Items"

        fun createInstance(jobList: ArrayList<JobModel>): MainFragment {
            val mainFragment = MainFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(ITEMS_COUNT_KEY, jobList)
            mainFragment.arguments = bundle
            return mainFragment
        }
    }

}