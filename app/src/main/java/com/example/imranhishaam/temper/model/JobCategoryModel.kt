package com.example.imranhishaam.temper.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by imranhishaam on 3/13/18.
 */

class JobCategoryModel : Parcelable {

    @SerializedName("description")
    var description: String? = null
    @SerializedName("icon_path")
    var iconPath: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.description)
        dest.writeString(this.iconPath)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.description = `in`.readString()
        this.iconPath = `in`.readString()
    }

    companion object {

        @JvmField val CREATOR: Parcelable.Creator<JobCategoryModel> = object : Parcelable.Creator<JobCategoryModel> {
            override fun createFromParcel(source: Parcel): JobCategoryModel {
                return JobCategoryModel(source)
            }

            override fun newArray(size: Int): Array<JobCategoryModel?>? {
                return arrayOfNulls(size)
            }
        }
    }
}
