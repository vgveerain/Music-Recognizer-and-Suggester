package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

public class status implements Parcelable{
    String msg;
    int code;

    public status(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Creator<status> getCREATOR() {
        return CREATOR;
    }

    protected status(Parcel in) {
        msg = in.readString();
        code = in.readInt();
    }

    public static final Creator<status> CREATOR = new Creator<status>() {
        @Override
        public status createFromParcel(Parcel in) {
            return new status(in);
        }

        @Override
        public status[] newArray(int size) {
            return new status[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeInt(code);
    }
}
