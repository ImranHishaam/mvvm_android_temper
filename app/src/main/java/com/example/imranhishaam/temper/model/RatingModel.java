package com.example.imranhishaam.temper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by imranhishaam on 3/13/18.
 */

public class RatingModel implements Parcelable {

    @SerializedName("average") private Double average;
    @SerializedName("count") private Integer count;
    @SerializedName("rated_client_id") private Integer ratedClientId;

    public void setAverage(Double average) {
        this.average = average;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setRatedClientId(Integer ratedClientId) {
        this.ratedClientId = ratedClientId;
    }

    public Double getAverage() {
        return average;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getRatedClientId() {
        return ratedClientId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.average);
        dest.writeValue(this.count);
        dest.writeValue(this.ratedClientId);
    }

    public RatingModel() {
    }

    protected RatingModel(Parcel in) {
        this.average = (Double) in.readValue(Double.class.getClassLoader());
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ratedClientId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<RatingModel> CREATOR = new Parcelable.Creator<RatingModel>() {
        @Override
        public RatingModel createFromParcel(Parcel source) {
            return new RatingModel(source);
        }

        @Override
        public RatingModel[] newArray(int size) {
            return new RatingModel[size];
        }
    };
}
