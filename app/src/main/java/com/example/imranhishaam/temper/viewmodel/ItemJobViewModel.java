/**
 * Copyright 2016 Erik imranhishaam Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.imranhishaam.temper.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.imranhishaam.temper.R;
import com.example.imranhishaam.temper.model.JobModel;
import com.example.imranhishaam.temper.view.JobDetailActivity;


public class ItemJobViewModel extends BaseObservable {

  private JobModel job;
  private Context context;

  public ItemJobViewModel(JobModel job, Context context) {
    this.job = job;
    this.context = context;
  }

  public String getClientName() {
    return job.getClient().getName();
  }

  public String getTitle() {
    return job.getTitle();
  }

  public String getDistance() {
    return String.valueOf(job.getDistance()) + " KM";
  }

  public String getRating(){
    if (job.getClient().getRating() == null) {
      return "⭐ 0.0";
    }
    return "⭐ " + String.valueOf(job.getClient().getRating().getAverage());
  }

  public String getCell() {
    return Integer.toString(job.getShifts().size()) + " open positie €" + Double.toString(job.getMaxPossibleEarningsHour()) + "/u" ;
  }

  public String getJobCategoryPicture() {
    return "https://temper.works/assets/img/icon-category-bediening.svg";
  }

  public String getPictureProfile() {
    return (String) job.getClient().getHero().get(0);
  }

  @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
    Glide.with(imageView.getContext()).load(url).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(imageView);
  }

  public void onItemClick(View view) {
    context.startActivity(JobDetailActivity.launchDetail(view.getContext(), job));
  }

  public void setJob(JobModel job) {
    this.job = job;
    notifyChange();
  }
}
