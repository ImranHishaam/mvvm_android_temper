package com.example.imranhishaam.temper.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by imranhishaam on 3/13/18.
 */

public class ShiftsModel implements Parcelable {

    private Integer tempersNeeded;
    private Integer earningsPerHour;
    private Integer duration;
    private String currency;
    private String startDate;
    private String startTime;
    private String startDatetime;
    private String endTime;
    private String endDatetime;

    public void setTempersNeeded(Integer tempersNeeded) {
        this.tempersNeeded = tempersNeeded;
    }

    public void setEarningsPerHour(Integer earningsPerHour) {
        this.earningsPerHour = earningsPerHour;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Integer getTempersNeeded() {

        return tempersNeeded;
    }

    public Integer getEarningsPerHour() {
        return earningsPerHour;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.tempersNeeded);
        dest.writeValue(this.earningsPerHour);
        dest.writeValue(this.duration);
        dest.writeString(this.currency);
        dest.writeString(this.startDate);
        dest.writeString(this.startTime);
        dest.writeString(this.startDatetime);
        dest.writeString(this.endTime);
        dest.writeString(this.endDatetime);
    }

    public ShiftsModel() {
    }

    protected ShiftsModel(Parcel in) {
        this.tempersNeeded = (Integer) in.readValue(Integer.class.getClassLoader());
        this.earningsPerHour = (Integer) in.readValue(Integer.class.getClassLoader());
        this.duration = (Integer) in.readValue(Integer.class.getClassLoader());
        this.currency = in.readString();
        this.startDate = in.readString();
        this.startTime = in.readString();
        this.startDatetime = in.readString();
        this.endTime = in.readString();
        this.endDatetime = in.readString();
    }

    public static final Parcelable.Creator<ShiftsModel> CREATOR = new Parcelable.Creator<ShiftsModel>() {
        @Override
        public ShiftsModel createFromParcel(Parcel source) {
            return new ShiftsModel(source);
        }

        @Override
        public ShiftsModel[] newArray(int size) {
            return new ShiftsModel[size];
        }
    };
}
