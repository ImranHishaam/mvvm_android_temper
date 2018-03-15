package com.example.imranhishaam.temper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Created by imranhishaam on 3/13/18.
 */

public class ClientModel implements Parcelable{

    @SerializedName("name") private String name;
    @SerializedName("id") private String id;
    @SerializedName("hero") private ArrayList<String> hero;
    @SerializedName("description") private String description;
    @SerializedName("rating") private RatingModel rating;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHero(ArrayList hero) {
        this.hero = hero;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(RatingModel rating) {
        this.rating = rating;
    }

    public String getName() {

        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList getHero() {
        return hero;
    }

    public String getDescription() {
        if (description == null) {
            return "";
        }
        return description;
    }

    public RatingModel getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeStringList(this.hero);
        dest.writeString(this.description);
        dest.writeParcelable(this.rating, flags);
    }

    public ClientModel() {
    }

    protected ClientModel(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.hero = in.createStringArrayList();
        this.description = in.readString();
        this.rating = in.readParcelable(RatingModel.class.getClassLoader());
    }

    public static final Creator<ClientModel> CREATOR = new Creator<ClientModel>() {
        @Override
        public ClientModel createFromParcel(Parcel source) {
            return new ClientModel(source);
        }

        @Override
        public ClientModel[] newArray(int size) {
            return new ClientModel[size];
        }
    };
}
