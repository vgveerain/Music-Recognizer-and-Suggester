package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageResponse implements Parcelable {
    album album;

    public ImageResponse(com.bumos.vgvee.musician.album album) {
        this.album = album;
    }

    protected ImageResponse(Parcel in) {
    }

    public static final Creator<ImageResponse> CREATOR = new Creator<ImageResponse>() {
        @Override
        public ImageResponse createFromParcel(Parcel in) {
            return new ImageResponse(in);
        }

        @Override
        public ImageResponse[] newArray(int size) {
            return new ImageResponse[size];
        }
    };

    public com.bumos.vgvee.musician.album getAlbum() {
        return album;
    }

    public void setAlbum(com.bumos.vgvee.musician.album album) {
        this.album = album;
    }

    public ImageResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
