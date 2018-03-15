package com.example.imranhishaam.temper.data;

/**
 * Created by imranhishaam on 3/13/18.
 */

import retrofit2.http.GET;
import retrofit2.http.Url;
import io.reactivex.Observable;

public interface JobService {

  @GET Observable<JobResponse> fetchJobs(@Url String url);

}
