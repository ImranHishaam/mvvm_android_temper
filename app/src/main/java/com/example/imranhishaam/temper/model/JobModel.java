package com.example.imranhishaam.temper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by imranhishaam on 3/13/18.
 */

public class JobModel implements Parcelable {

    @SerializedName("title") private String title;
    @SerializedName("id") private Integer id;
    @SerializedName("date") private String date;
    @SerializedName("distance") private Integer distance;
    @SerializedName("url") private String url;
    @SerializedName("max_possible_earnings_hour") private Double maxPossibleEarningsHour;
    @SerializedName("max_possible_earnings_total") private Double maxPossibleEarningsTotal;
    @SerializedName("client") private ClientModel client;
    @SerializedName("jobCategoryModel") private JobCategoryModel jobCategoryModel;
    @SerializedName("matches") private ArrayList<String> matches;
    @SerializedName("accepted_matches_count") private Integer acceptedMatchesCount;
    @SerializedName("new_matches_count") private Integer newMatchesCount;
    @SerializedName("shifts") private ArrayList<ShiftsModel> shifts;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMaxPossibleEarningsHour(Double maxPossibleEarningsHour) {
        this.maxPossibleEarningsHour = maxPossibleEarningsHour;
    }

    public void setMaxPossibleEarningsTotal(Double maxPossibleEarningsTotal) {
        this.maxPossibleEarningsTotal = maxPossibleEarningsTotal;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public void setJobCategoryModel(JobCategoryModel jobCategoryModel) {
        this.jobCategoryModel = jobCategoryModel;
    }

    public void setMatches(ArrayList<String> matches) {
        this.matches = matches;
    }

    public void setAcceptedMatchesCount(Integer acceptedMatchesCount) {
        this.acceptedMatchesCount = acceptedMatchesCount;
    }

    public void setNewMatchesCount(Integer newMatchesCount) {
        this.newMatchesCount = newMatchesCount;
    }

    public void setShifts(ArrayList<ShiftsModel> shifts) {
        this.shifts = shifts;
    }

    public String getTitle() {

        return title;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getUrl() {
        return url;
    }

    public Double getMaxPossibleEarningsHour() {
        if (maxPossibleEarningsHour == null) {
            return 0.0;
        }
        return maxPossibleEarningsHour;
    }

    public Double getMaxPossibleEarningsTotal() {
        return maxPossibleEarningsTotal;
    }

    public ClientModel getClient() {
        return client;
    }

    public JobCategoryModel getJobCategoryModel() {
        return jobCategoryModel;
    }

    public ArrayList<String> getMatches() {
        return matches;
    }

    public Integer getAcceptedMatchesCount() {
        return acceptedMatchesCount;
    }

    public Integer getNewMatchesCount() {
        return newMatchesCount;
    }

    public ArrayList<ShiftsModel> getShifts() {
        return shifts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeValue(this.id);
        dest.writeString(this.date);
        dest.writeValue(this.distance);
        dest.writeString(this.url);
        dest.writeValue(this.maxPossibleEarningsHour);
        dest.writeValue(this.maxPossibleEarningsTotal);
        dest.writeParcelable(this.client, flags);
        dest.writeParcelable(this.jobCategoryModel, flags);
        dest.writeStringList(this.matches);
        dest.writeValue(this.acceptedMatchesCount);
        dest.writeValue(this.newMatchesCount);
        dest.writeTypedList(this.shifts);
    }

    public JobModel() {
    }

    protected JobModel(Parcel in) {
        this.title = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.date = in.readString();
        this.distance = (Integer) in.readValue(Integer.class.getClassLoader());
        this.url = in.readString();
        this.maxPossibleEarningsHour = (Double) in.readValue(Double.class.getClassLoader());
        this.maxPossibleEarningsTotal = (Double) in.readValue(Double.class.getClassLoader());
        this.client = in.readParcelable(ClientModel.class.getClassLoader());
        this.jobCategoryModel = in.readParcelable(JobCategoryModel.class.getClassLoader());
        this.matches = in.createStringArrayList();
        this.acceptedMatchesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.newMatchesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shifts = in.createTypedArrayList(ShiftsModel.CREATOR);
    }

    public static final Creator<JobModel> CREATOR = new Creator<JobModel>() {
        @Override
        public JobModel createFromParcel(Parcel source) {
            return new JobModel(source);
        }

        @Override
        public JobModel[] newArray(int size) {
            return new JobModel[size];
        }
    };
}
