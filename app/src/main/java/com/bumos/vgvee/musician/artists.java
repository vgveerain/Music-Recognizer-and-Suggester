package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

public class artists implements Parcelable{
    String name;

    public String getName() {
        return name;
    }

    public artists(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<artists> getCREATOR() {
        return CREATOR;
    }

    protected artists(Parcel in) {
        name = in.readString();
    }

    public static final Creator<artists> CREATOR = new Creator<artists>() {
        @Override
        public artists createFromParcel(Parcel in) {
            return new artists(in);
        }

        @Override
        public artists[] newArray(int size) {
            return new artists[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
