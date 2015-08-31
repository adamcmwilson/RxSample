package com.adamwilson.common.api.photos.response;

import com.adamwilson.common.model.Photo;

import java.util.List;

public class GetPhotosResponse {

    int         current_page;
    int         total_pages;
    int         total_items;
    List<Photo> photos;
    String      feature;

    public int getCurrentPage() { return current_page; }

    public int getTotalPages() { return total_pages; }

    public int getTotalItems() { return total_items; }

    public List<Photo> getPhotos() { return photos; }

    public String getFeature() { return feature; }

    @Override public String toString() {
        return "GetPhotosResponse{" +
                "current_page=" + current_page +
                ", total_pages=" + total_pages +
                ", total_items=" + total_items +
                ", photos=" + photos +
                ", feature='" + feature + '\'' +
                '}';
    }
}
