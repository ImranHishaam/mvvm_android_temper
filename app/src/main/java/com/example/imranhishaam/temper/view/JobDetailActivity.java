package com.example.imranhishaam.temper.view;

/**
 * Created by imranhishaam on 3/13/18.
 */

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.imranhishaam.temper.R;
import com.example.imranhishaam.temper.databinding.JobDetailActivityBinding;
import com.example.imranhishaam.temper.model.JobModel;
import com.example.imranhishaam.temper.viewmodel.JobDetailViewModel;

public class JobDetailActivity extends AppCompatActivity {

  private static final String EXTRA_JOB = "EXTRA_JOB";

  private JobDetailActivityBinding jobDetailActivityBinding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    jobDetailActivityBinding =
        DataBindingUtil.setContentView(this, R.layout.job_detail_activity);
    setSupportActionBar(jobDetailActivityBinding.toolbar);
    displayHomeAsUpEnabled();
    getExtrasFromIntent();
  }

  public static Intent launchDetail(Context context, JobModel job) {
    Intent intent = new Intent(context, JobDetailActivity.class);
    intent.putExtra(EXTRA_JOB, job);
    return intent;
  }

  private void displayHomeAsUpEnabled() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  private void getExtrasFromIntent() {
    JobModel job = (JobModel) getIntent().getParcelableExtra(EXTRA_JOB);
    JobDetailViewModel jobDetailViewModel = new JobDetailViewModel(job);
    jobDetailActivityBinding.setJobDetailViewModel(jobDetailViewModel);
    setTitle(job.getTitle() + "." + job.getClient() + " " + job.getTitle());
  }
}
