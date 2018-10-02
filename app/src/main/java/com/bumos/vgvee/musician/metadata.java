package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class metadata implements Parcelable{
    ArrayList<music> music;

    public ArrayList<com.bumos.vgvee.musician.music> getMusic() {
        return music;
    }

    public metadata(ArrayList<com.bumos.vgvee.musician.music> music) {
        this.music = music;
    }

    public void setMusic(ArrayList<com.bumos.vgvee.musician.music> music) {
        this.music = music;
    }

    public static Creator<metadata> getCREATOR() {
        return CREATOR;
    }

    protected metadata(Parcel in) {
    }

    public static final Creator<metadata> CREATOR = new Creator<metadata>() {
        @Override
        public metadata createFromParcel(Parcel in) {
            return new metadata(in);
        }

        @Override
        public metadata[] newArray(int size) {
            return new metadata[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
