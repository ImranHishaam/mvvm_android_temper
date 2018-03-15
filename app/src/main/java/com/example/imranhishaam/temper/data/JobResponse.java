package com.example.imranhishaam.temper.data;

/**
 * Created by imranhishaam on 3/13/18.
 */

import com.example.imranhishaam.temper.model.JobModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class JobResponse {

  @SerializedName("results") private Map<String, ArrayList<JobModel>> jobList;

  public Map<String, ArrayList<JobModel>> getJobList() {
    return jobList;
  }

  public void setJobList(Map<String, ArrayList<JobModel>> mJobList) {
    this.jobList = mJobList;
  }
}
