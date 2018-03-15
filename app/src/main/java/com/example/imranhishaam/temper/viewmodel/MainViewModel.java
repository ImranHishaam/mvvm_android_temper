/**
 * Copyright 2016 Erik imranhishaam Rey. <p/> Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at <p/> http://www.apache.org/licenses/LICENSE-2.0 <p/> Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package com.example.imranhishaam.temper.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.imranhishaam.temper.BuildConfig;
import com.example.imranhishaam.temper.TemperApplication;
import com.example.imranhishaam.temper.R;
import com.example.imranhishaam.temper.data.JobFactory;
import com.example.imranhishaam.temper.data.JobResponse;
import com.example.imranhishaam.temper.data.JobService;
import com.example.imranhishaam.temper.model.JobModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class MainViewModel extends Observable {

  public ObservableInt jobProgress;
  public ObservableInt jobRecycler;
  public ObservableInt jobLabel;
  public ObservableField<String> messageLabel;

  private Map<String, ArrayList<JobModel>> jobList;
  private Context context;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public MainViewModel(@NonNull Context context) {

    this.context = context;
    this.jobList = new HashMap<String, ArrayList<JobModel>>();
    jobProgress = new ObservableInt(View.GONE);
    jobRecycler = new ObservableInt(View.GONE);
    jobLabel = new ObservableInt(View.VISIBLE);
    messageLabel = new ObservableField<>(context.getString(R.string.default_loading_job));
  }

  public void onClickFabLoad(View view) {
    initializeViews();
    fetchJobList();
  }

  private void initializeViews() {
    jobLabel.set(View.GONE);
    jobRecycler.set(View.GONE);
    jobProgress.set(View.VISIBLE);
  }

  public void fetchJobList() {

    TemperApplication temperApplication = TemperApplication.create(context);
    JobService jobService = temperApplication.getJobService();

    Disposable disposable = jobService.fetchJobs(BuildConfig.HOST_URL + BuildConfig.JOBS_URL)
        .subscribeOn(temperApplication.subscribeScheduler())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<JobResponse>() {
          @Override public void accept(JobResponse jobResponse) throws Exception {
            changeJobDataSet(jobResponse.getJobList());
            jobProgress.set(View.GONE);
            jobLabel.set(View.GONE);
            jobRecycler.set(View.VISIBLE);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            messageLabel.set(context.getString(R.string.error_loading_job));
            jobProgress.set(View.GONE);
            jobLabel.set(View.VISIBLE);
            jobRecycler.set(View.GONE);
          }
        });

    compositeDisposable.add(disposable);
  }

    private void changeJobDataSet(Map<String, ArrayList<JobModel>> jobs) {
      jobList.putAll(jobs);
      setChanged();
      notifyObservers();
    }

  public Map<String, ArrayList<JobModel>> getJobList() {
    return jobList;
  }

  private void unSubscribeFromObservable() {
    if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  public void reset() {
    unSubscribeFromObservable();
    compositeDisposable = null;
    context = null;
  }
}
