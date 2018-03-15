package com.example.imranhishaam.temper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by imranhishaam on 3/13/18.
 */

public class JobCategoryModel implements Parcelable {

    @SerializedName("description") private String description;
    @SerializedName("icon_path") private String iconPath;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getDescription() {

        return description;
    }

    public String getIconPath() {
        return iconPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.iconPath);
    }

    public JobCategoryModel() {
    }

    protected JobCategoryModel(Parcel in) {
        this.description = in.readString();
        this.iconPath = in.readString();
    }

    public static final Parcelable.Creator<JobCategoryModel> CREATOR = new Parcelable.Creator<JobCategoryModel>() {
        @Override
        public JobCategoryModel createFromParcel(Parcel source) {
            return new JobCategoryModel(source);
        }

        @Override
        public JobCategoryModel[] newArray(int size) {
            return new JobCategoryModel[size];
        }
    };
}
