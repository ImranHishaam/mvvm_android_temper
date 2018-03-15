/**
 * Copyright 2016 Erik imranhishaam Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.imranhishaam.temper.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.imranhishaam.temper.R;
import com.example.imranhishaam.temper.model.JobModel;

public class JobDetailViewModel {

  private JobModel job;

  public JobDetailViewModel(JobModel job) {
    this.job = job;
  }

  public String getFullUserName() {
    return job.getClient().getName();
  }

  public String getDescription(){
    return job.getClient().getDescription();
  }

  public String getUserName() {
    return job.getClient().getName();
  }

  public String getDate() {
    return job.getDate();
  }

  public String getCell() {
    return job.getDistance() + " KM";
  }

  public String getPictureProfile() {
    return (String) job.getClient().getHero().get(0);
  }

  public String getUrl() {
    return job.getUrl();
  }

  @BindingAdapter({"imageUrl"})
  public static void loadImage(ImageView view, String imageUrl) {
    Glide.with(view.getContext()).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(view);
  }
}
