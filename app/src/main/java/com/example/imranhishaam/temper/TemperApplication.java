package com.example.imranhishaam.temper;

/**
 * Created by imranhishaam on 3/13/18.
 */

import android.app.Application;
import android.content.Context;

import com.example.imranhishaam.temper.data.JobFactory;
import com.example.imranhishaam.temper.data.JobService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TemperApplication extends Application {

  private JobService jobService;
  private Scheduler scheduler;

  private static TemperApplication get(Context context) {
    return (TemperApplication) context.getApplicationContext();
  }

  public static TemperApplication create(Context context) {
    return TemperApplication.get(context);
  }

  public JobService getJobService() {
    if (jobService == null) {
      jobService = JobFactory.create();
    }

    return jobService;
  }

  public Scheduler subscribeScheduler() {
    if (scheduler == null) {
      scheduler = Schedulers.io();
    }

    return scheduler;
  }

  public void setJobService(JobService jobService) {
    this.jobService = jobService;
  }

  public void setScheduler(Scheduler scheduler) {
    this.scheduler = scheduler;
  }
}
