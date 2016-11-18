package com.example.axtonsun.nicedemo.MVP.Bean;

/**
 * Created by AxtonSun on 2016/11/17.
 */

public class User {
    private String login;
    private String name;
    private int followers;
    private int following;

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
