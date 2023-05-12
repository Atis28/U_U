package com.example.muyu_u_.model;

public class Music {
    private String title;
    private String bio;
    private String time;
    private String url;

    public Music() {
    }

    public Music(String title, String bio, String time, String url) {
        this.title = title;
        this.bio = bio;
        this.time = time;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
