package com.bumos.vgvee.musician;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class music implements Parcelable{
    album album;
    String label;
    String title;
    ArrayList<artists> artists;

    public music(com.bumos.vgvee.musician.album album, String label, String title, ArrayList<com.bumos.vgvee.musician.artists> artists) {
        this.album = album;
        this.label = label;
        this.title = title;
        this.artists = artists;
    }

    public com.bumos.vgvee.musician.album getAlbum() {
        return album;
    }

    public void setAlbum(com.bumos.vgvee.musician.album album) {
        this.album = album;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<com.bumos.vgvee.musician.artists> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<com.bumos.vgvee.musician.artists> artists) {
        this.artists = artists;
    }

    public static Creator<music> getCREATOR() {
        return CREATOR;
    }

    protected music(Parcel in) {
        album = in.readParcelable(com.bumos.vgvee.musician.album.class.getClassLoader());
        label = in.readString();
        title = in.readString();
        artists = in.createTypedArrayList(com.bumos.vgvee.musician.artists.CREATOR);
    }

    public static final Creator<music> CREATOR = new Creator<music>() {
        @Override
        public music createFromParcel(Parcel in) {
            return new music(in);
        }

        @Override
        public music[] newArray(int size) {
            return new music[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(album, flags);
        dest.writeString(label);
        dest.writeString(title);
        dest.writeTypedList(artists);
    }
}
