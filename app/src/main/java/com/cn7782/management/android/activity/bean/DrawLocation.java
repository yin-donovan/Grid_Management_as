package com.cn7782.management.android.activity.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DrawLocation implements Comparable<DrawLocation>, Parcelable {
    public double Latitude;
    public double Longitude;
    public double speed;
    public String time;

    public DrawLocation() {

    }

    public DrawLocation(Parcel in) {
        Latitude = in.readDouble();
        Longitude = in.readDouble();
        speed = in.readDouble();
        time = in.readString();
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeDouble(Latitude);
        dest.writeDouble(Longitude);
        dest.writeDouble(speed);
        dest.writeString(time);
    }

    @Override
    public int compareTo(DrawLocation another) {
        // TODO Auto-generated method stub
        if (null != time && null != another && null != another.time) {
            return time.compareTo(another.time);
        }
        return 0;
    }

    // 协议,不写报错,妹的
    public static final Parcelable.Creator<DrawLocation> CREATOR = new Parcelable.Creator<DrawLocation>() {
        public DrawLocation createFromParcel(Parcel in) {
            return new DrawLocation(in);
        }

        @Override
        public DrawLocation[] newArray(int size) {
            return new DrawLocation[size];
        }
    };
}
