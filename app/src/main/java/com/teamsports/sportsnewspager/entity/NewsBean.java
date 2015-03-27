package com.teamsports.sportsnewspager.entity;

/**
 * Created by HTao on 2015/3/25.
 */
public class NewsBean {
    private String title;
    private String image;
    private String show_count;
    private String url;

    public NewsBean(String title, String image, String show_count, String url) {
        this.title = title;
        this.image = image;
        this.show_count = show_count;
        this.url = url;
    }

    public NewsBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShow_count() {
        return show_count;
    }

    public void setShow_count(String show_count) {
        this.show_count = show_count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
