package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

public class album implements Parcelable {
    String name;

    public String getName() {
        return name;
    }

    public album(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<album> getCREATOR() {
        return CREATOR;
    }

    protected album(Parcel in) {
        name = in.readString();
    }

    public static final Creator<album> CREATOR = new Creator<album>() {
        @Override
        public album createFromParcel(Parcel in) {
            return new album(in);
        }

        @Override
        public album[] newArray(int size) {
            return new album[size];
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
