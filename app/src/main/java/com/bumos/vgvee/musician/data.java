package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class data implements Parcelable{
    ArrayList<String> data;
    int year;

    public data() {
    }

    public data(ArrayList<String> data) {
        this.data = data;
        this.year = 0;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    protected data(Parcel in) {
        data = in.createStringArrayList();
    }

    public static final Creator<data> CREATOR = new Creator<data>() {
        @Override
        public data createFromParcel(Parcel in) {
            return new data(in);
        }

        @Override
        public data[] newArray(int size) {
            return new data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(data);
    }
}
