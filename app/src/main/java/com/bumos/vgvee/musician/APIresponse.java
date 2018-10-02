package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

public class APIresponse implements Parcelable {
    status status;
    metadata metadata;

    public APIresponse(com.bumos.vgvee.musician.status status, com.bumos.vgvee.musician.metadata metadata) {
        this.status = status;
        this.metadata = metadata;
    }

    public com.bumos.vgvee.musician.status getStatus() {
        return status;
    }

    public void setStatus(com.bumos.vgvee.musician.status status) {
        this.status = status;
    }

    public com.bumos.vgvee.musician.metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(com.bumos.vgvee.musician.metadata metadata) {
        this.metadata = metadata;
    }

    public static Creator<APIresponse> getCREATOR() {
        return CREATOR;
    }

    protected APIresponse(Parcel in) {
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
    }
}
