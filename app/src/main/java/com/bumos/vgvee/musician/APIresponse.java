package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class APIresponse implements Parcelable{
    ArrayList<String> result;

    public APIresponse(ArrayList<String> result) {
        this.result = result;
    }

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(ArrayList<String> result) {
        this.result = result;
    }

    protected APIresponse(Parcel in) {
        result = in.createStringArrayList();
    }

    public static final Creator<APIresponse> CREATOR = new Creator<APIresponse>() {
        @Override
        public APIresponse createFromParcel(Parcel in) {
            return new APIresponse(in);
        }

        @Override
        public APIresponse[] newArray(int size) {
            return new APIresponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(result);
    }
}
