package com.example.muyu_u_.model;

public class Music {
    private String title;
    private String time;
    private String url;

    public Music() {
        // No-argument constructor
    }

    public Music(String title, long time, String url) {
    }

    public Music(String title, String time, String url) {
        this.title = title;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
