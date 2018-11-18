package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class APIresponse2 implements Parcelable {
    ArrayList<data> data;

    public APIresponse2() {
    }

    public ArrayList<com.bumos.vgvee.musician.data> getData() {
        return data;
    }

    public void setData(ArrayList<com.bumos.vgvee.musician.data> data) {
        this.data = data;
    }

    public APIresponse2(ArrayList<com.bumos.vgvee.musician.data> data) {
        this.data = data;
    }

    protected APIresponse2(Parcel in) {
        data = in.createTypedArrayList(com.bumos.vgvee.musician.data.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<APIresponse2> CREATOR = new Creator<APIresponse2>() {
        @Override
        public APIresponse2 createFromParcel(Parcel in) {
            return new APIresponse2(in);
        }

        @Override
        public APIresponse2[] newArray(int size) {
            return new APIresponse2[size];
        }
    };
}
