package com.example.imranhishaam.temper.view;

/**
 * Created by imranhishaam on 3/13/18.
 */

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.imranhishaam.temper.R;
import com.example.imranhishaam.temper.databinding.ItemJobBinding;
import com.example.imranhishaam.temper.model.JobModel;
import com.example.imranhishaam.temper.viewmodel.ItemJobViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.JobAdapterViewHolder> {

  private List<JobModel> jobList;

  public MainAdapter() {
    this.jobList = Collections.emptyList();
  }

  @Override public JobAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemJobBinding itemJobBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_job,
            parent, false);
    return new JobAdapterViewHolder(itemJobBinding);
  }

  @Override public void onBindViewHolder(JobAdapterViewHolder holder, int position) {
    holder.bindJobs(jobList.get(position));
  }

  @Override public int getItemCount() {
    return jobList.size();
  }

  public void setJobList(ArrayList<JobModel> jobList) {
    this.jobList = jobList;
    notifyDataSetChanged();
  }

  public static class JobAdapterViewHolder extends RecyclerView.ViewHolder {
    ItemJobBinding mItemJobBinding;

    public JobAdapterViewHolder(ItemJobBinding itemJobBinding) {
      super(itemJobBinding.itemJob);
      this.mItemJobBinding = itemJobBinding;
    }

    void bindJobs(JobModel job) {
      if (mItemJobBinding.getJobViewModel() == null) {
        mItemJobBinding.setJobViewModel(new ItemJobViewModel(job, itemView.getContext()));
      } else {
        mItemJobBinding.getJobViewModel().setJob(job);
      }
    }
  }
}
