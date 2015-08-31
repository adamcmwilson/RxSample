package com.adamwilson.common.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    int    id;
    String username;
    String firstname;
    String lastname;
    String fullname;
    String city;
    String country;
    String userpic_url;
    int    upgrade_status;
    int    followers_count;
    int    affection;

    public User() {}

    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getFullname() { return fullname; }

    public String getCity() { return city; }

    public String getCountry() { return country; }

    public String getUserpicUrl() { return userpic_url; }

    public int getUpgradeStatus() { return upgrade_status; }

    public int getFollowersCount() { return followers_count; }

    public int getAffection() { return affection; }

    @Override public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", userpic_url='" + userpic_url + '\'' +
                ", upgrade_status=" + upgrade_status +
                ", followers_count=" + followers_count +
                ", affection=" + affection +
                '}';
    }


    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.username);
        dest.writeString(this.firstname);
        dest.writeString(this.lastname);
        dest.writeString(this.fullname);
        dest.writeString(this.city);
        dest.writeString(this.country);
        dest.writeString(this.userpic_url);
        dest.writeInt(this.upgrade_status);
        dest.writeInt(this.followers_count);
        dest.writeInt(this.affection);
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.username = in.readString();
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.fullname = in.readString();
        this.city = in.readString();
        this.country = in.readString();
        this.userpic_url = in.readString();
        this.upgrade_status = in.readInt();
        this.followers_count = in.readInt();
        this.affection = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {return new User(source);}

        public User[] newArray(int size) {return new User[size];}
    };
}