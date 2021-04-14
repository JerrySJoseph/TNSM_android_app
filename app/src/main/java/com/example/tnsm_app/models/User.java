package com.example.tnsm_app.models;

import com.example.js_auth.Models.JSCloudUser;
import com.google.gson.Gson;

import java.util.ArrayList;

public class User extends JSCloudUser {

    ArrayList<String> highlightImageUrls;
    int followerCount,followingCount;

    String bio,tagline;
    long DOB;

    public long getDOB() {
        return DOB;
    }

    public String getTagline() {
        return tagline;
    }


    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setDOB(long DOB) {
        this.DOB = DOB;
    }

    public ArrayList<String> getHighlightImageUrls() {
        return highlightImageUrls;
    }

    public void setHighlightImageUrls(ArrayList<String> highlightImageUrls) {
        this.highlightImageUrls = highlightImageUrls;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public static User fromJSON(String jsonString){
        return new Gson().fromJson(jsonString,User.class);
    }
}
