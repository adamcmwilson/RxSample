package com.adamwilson.common.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;

public class Image implements Parcelable {

    int    size;
    String url;
    String https_url;
    String format;

    @VisibleForTesting public Image(int size,
                                    String url,
                                    String https_url,
                                    String format) {
        this.size = size;
        this.url = url;
        this.https_url = https_url;
        this.format = format;
    }

    public int getSize() { return size; }

    public String getUrl() { return url; }

    public String getHttpsUrl() { return https_url; }

    public String getFormat() { return format; }

    @Override public String toString() {
        return "Image{" +
                "size=" + size +
                ", url='" + url + '\'' +
                ", https_url='" + https_url + '\'' +
                ", format='" + format + '\'' +
                '}';
    }

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.size);
        dest.writeString(this.url);
        dest.writeString(this.https_url);
        dest.writeString(this.format);
    }

    protected Image(Parcel in) {
        this.size = in.readInt();
        this.url = in.readString();
        this.https_url = in.readString();
        this.format = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        public Image createFromParcel(Parcel source) {return new Image(source);}

        public Image[] newArray(int size) {return new Image[size];}
    };
}
