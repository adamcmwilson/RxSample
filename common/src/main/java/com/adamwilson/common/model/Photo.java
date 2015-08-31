package com.adamwilson.common.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;

public class Photo implements Parcelable {

    int              id;
    String           name;
    String           description;
    String           camera;
    String           lens;
    String           focal_length;
    String           iso;
    String           shutter_speed;
    String           aperture;
    int              times_viewed;
    double           rating;
    int              status;
    String           created_at;
    int              category;
    String           location;
    boolean          privacy;
    double           latitude;
    double           longitude;
    String           taken_at;
    boolean          for_sale;
    int              width;
    int              height;
    int              votes_count;
    int              favorites_count;
    int              comments_count;
    int              positive_votes_count;
    boolean          nsfw;
    int              sales_count;
    double           highest_rating;
    String           highest_rating_date;
    String           license_type;
    ArrayList<Image> images;
    User             user;
    int              collections_count;

    public int getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getCamera() { return camera; }

    public String getLens() { return lens; }

    public String getFocalLength() { return focal_length; }

    public String getIso() { return iso; }

    public String getShutterSpeed() { return shutter_speed; }

    public String getAperture() { return aperture; }

    public int getTimesViewed() { return times_viewed; }

    public double getRating() { return rating; }

    public int getStatus() { return status; }

    public String getCreatedAt() { return created_at; }

    public int getCategory() { return category; }

    public String getLocation() { return location; }

    public boolean isPrivate() { return privacy; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public String getTakenAt() { return taken_at; }

    public boolean isForSale() { return for_sale; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public int getVotesCount() { return votes_count; }

    public int getFavoritesCount() { return favorites_count; }

    public int getCommentsCount() { return comments_count; }

    public int getPositiveVotesCount() { return positive_votes_count; }

    public boolean isNsfw() { return nsfw; }

    public int getSalesCount() { return sales_count; }

    public double getHighestRating() { return highest_rating; }

    public String getHighestRatingDate() { return highest_rating_date; }

    public String getLicenseType() { return license_type; }

    @VisibleForTesting public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ArrayList<Image> getImages() { return images; }

    public Image getImage(final int imageSize) {
        if (images == null || images.isEmpty()) return null;

        for (Image image : images) {
            if (image.getSize() == imageSize)
                return image;
        }
        return null;
    }

    public User getUser() { return user; }

    public int getCollectionsCount() { return collections_count; }

    @Override public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", camera='" + camera + '\'' +
                ", lens='" + lens + '\'' +
                ", focal_length='" + focal_length + '\'' +
                ", iso='" + iso + '\'' +
                ", shutter_speed='" + shutter_speed + '\'' +
                ", aperture='" + aperture + '\'' +
                ", times_viewed=" + times_viewed +
                ", rating=" + rating +
                ", status=" + status +
                ", created_at='" + created_at + '\'' +
                ", category=" + category +
                ", location='" + location + '\'' +
                ", privacy=" + privacy +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", taken_at='" + taken_at + '\'' +
                ", for_sale=" + for_sale +
                ", width=" + width +
                ", height=" + height +
                ", votes_count=" + votes_count +
                ", favorites_count=" + favorites_count +
                ", comments_count=" + comments_count +
                ", positive_votes_count=" + positive_votes_count +
                ", nsfw=" + nsfw +
                ", sales_count=" + sales_count +
                ", highest_rating=" + highest_rating +
                ", highest_rating_date='" + highest_rating_date + '\'' +
                ", license_type='" + license_type + '\'' +
                ", images=" + images +
                ", user=" + user +
                ", collections_count=" + collections_count +
                '}';
    }


    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.camera);
        dest.writeString(this.lens);
        dest.writeString(this.focal_length);
        dest.writeString(this.iso);
        dest.writeString(this.shutter_speed);
        dest.writeString(this.aperture);
        dest.writeInt(this.times_viewed);
        dest.writeDouble(this.rating);
        dest.writeInt(this.status);
        dest.writeString(this.created_at);
        dest.writeInt(this.category);
        dest.writeString(this.location);
        dest.writeByte(privacy ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.taken_at);
        dest.writeByte(for_sale ? (byte) 1 : (byte) 0);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.votes_count);
        dest.writeInt(this.favorites_count);
        dest.writeInt(this.comments_count);
        dest.writeInt(this.positive_votes_count);
        dest.writeByte(nsfw ? (byte) 1 : (byte) 0);
        dest.writeInt(this.sales_count);
        dest.writeDouble(this.highest_rating);
        dest.writeString(this.highest_rating_date);
        dest.writeString(this.license_type);
        dest.writeTypedList(images);
        dest.writeParcelable(this.user, 0);
        dest.writeInt(this.collections_count);
    }

    public Photo() {}

    protected Photo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.camera = in.readString();
        this.lens = in.readString();
        this.focal_length = in.readString();
        this.iso = in.readString();
        this.shutter_speed = in.readString();
        this.aperture = in.readString();
        this.times_viewed = in.readInt();
        this.rating = in.readDouble();
        this.status = in.readInt();
        this.created_at = in.readString();
        this.category = in.readInt();
        this.location = in.readString();
        this.privacy = in.readByte() != 0;
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.taken_at = in.readString();
        this.for_sale = in.readByte() != 0;
        this.width = in.readInt();
        this.height = in.readInt();
        this.votes_count = in.readInt();
        this.favorites_count = in.readInt();
        this.comments_count = in.readInt();
        this.positive_votes_count = in.readInt();
        this.nsfw = in.readByte() != 0;
        this.sales_count = in.readInt();
        this.highest_rating = in.readDouble();
        this.highest_rating_date = in.readString();
        this.license_type = in.readString();
        this.images = in.createTypedArrayList(Image.CREATOR);
        this.user = in.readParcelable(User.class.getClassLoader());
        this.collections_count = in.readInt();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        public Photo createFromParcel(Parcel source) {return new Photo(source);}

        public Photo[] newArray(int size) {return new Photo[size];}
    };
}