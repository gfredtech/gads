package me.gfred.gads.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoursEntry implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("hours")
    @Expose
    private Integer hours;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("badgeUrl")
    @Expose
    private String badgeUrl;

    protected HoursEntry(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            hours = null;
        } else {
            hours = in.readInt();
        }
        country = in.readString();
        badgeUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (hours == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hours);
        }
        dest.writeString(country);
        dest.writeString(badgeUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HoursEntry> CREATOR = new Creator<HoursEntry>() {
        @Override
        public HoursEntry createFromParcel(Parcel in) {
            return new HoursEntry(in);
        }

        @Override
        public HoursEntry[] newArray(int size) {
            return new HoursEntry[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

}